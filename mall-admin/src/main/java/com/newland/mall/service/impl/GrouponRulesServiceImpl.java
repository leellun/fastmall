package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Goods;
import com.newland.mall.entity.GrouponRules;
import com.newland.mall.enums.GrouponRulesStatusEnum;
import com.newland.mall.mapper.GoodsMapper;
import com.newland.mall.mapper.GrouponRulesMapper;
import com.newland.mall.model.vo.wx.GrouponRuleVO;
import com.newland.mall.service.GoodsService;
import com.newland.mall.service.GrouponRulesService;
import com.newland.mall.task.GrouponRuleExpiredTask;
import com.newland.mall.task.TaskService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * 团购规则表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class GrouponRulesServiceImpl extends ServiceImpl<GrouponRulesMapper, GrouponRules> implements GrouponRulesService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private TaskService taskService;

    @Override
    public PageInfo<GrouponRules> listGrouponRules(Long goodsId, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listByGoodsId(goodsId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRules(GrouponRules grouponRules) {
        validate(grouponRules);
        Goods goods = goodsMapper.selectByPrimaryKey(grouponRules.getGoodsId());
        AssertUtil.notNull(goods, "团购商品不存在");
        AssertUtil.isTrue(baseMapper.countByGoodsId(grouponRules.getGoodsId()) == 0, "团购商品已经存在");


        grouponRules.setGoodsName(goods.getName());
        grouponRules.setPicUrl(goods.getPicUrl());
        grouponRules.setStatus(GrouponRulesStatusEnum.RULE_STATUS_ON.getKey());
        baseMapper.insert(grouponRules);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expire = grouponRules.getExpireTime();
        long delay = ChronoUnit.MILLIS.between(now, expire);
        // 团购过期任务
        taskService.addTask(new GrouponRuleExpiredTask(grouponRules.getId(), delay));
    }

    @Override
    public void update(GrouponRules grouponRules) {
        validate(grouponRules);

        GrouponRules rules = baseMapper.selectByPrimaryKey(grouponRules.getId());
        AssertUtil.notNull(rules, "规则不存在");
        AssertUtil.isTrue(rules.getStatus().equals(GrouponRulesStatusEnum.RULE_STATUS_ON.getKey()), "团购已经下线");

        Goods goods = goodsMapper.selectByPrimaryKey(grouponRules.getGoodsId());
        AssertUtil.notNull(goods, "商品不存在");

        grouponRules.setGoodsName(goods.getName());
        grouponRules.setPicUrl(goods.getPicUrl());
        baseMapper.updateByPrimaryKeySelective(grouponRules);
    }

    @Override
    public void delete(Long id) {
        GrouponRules t = new GrouponRules();
        t.setId(id);
        t.setDeleted(1);
        AssertUtil.isTrue(baseMapper.updateByPrimaryKeySelective(t) > 0, "删除失败");
    }

    @Override
    public List<GrouponRules> listByStatus(Integer status) {
        return baseMapper.listByStatus(status);
    }

    @Override
    public PageInfo<GrouponRuleVO> listGrouponRuleVO(PageEntity pageEntity) {
        PageInfo<GrouponRules> pageInfo = PageWrapper.page(pageEntity, () -> baseMapper.list());
        List<GrouponRules> grouponRulesList = pageInfo.getList();

        List<GrouponRuleVO> grouponList = new ArrayList<>();

        for (GrouponRules rule : grouponRulesList) {
            Long goodsId = rule.getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            if (goods == null) {
                continue;
            }

            GrouponRuleVO grouponRuleVo = new GrouponRuleVO();
            grouponRuleVo.setId(goods.getId());
            grouponRuleVo.setName(goods.getName());
            grouponRuleVo.setBrief(goods.getBrief());
            grouponRuleVo.setPicUrl(goods.getPicUrl());
            grouponRuleVo.setCounterPrice(goods.getCounterPrice());
            grouponRuleVo.setRetailPrice(goods.getRetailPrice());
            grouponRuleVo.setGrouponPrice(goods.getRetailPrice().subtract(rule.getDiscount()));
            grouponRuleVo.setGrouponDiscount(rule.getDiscount());
            grouponRuleVo.setGrouponMember(rule.getDiscountMember());
            grouponRuleVo.setExpireTime(rule.getExpireTime());
            grouponList.add(grouponRuleVo);
        }
        return PageWrapper.newPageInfo(pageInfo,grouponList);
    }
    
    /**
     * 校验
     *
     * @param grouponRules
     */
    private void validate(GrouponRules grouponRules) {
        AssertUtil.notNull(grouponRules.getGoodsId(), "商品不存在");
        AssertUtil.notNull(grouponRules.getDiscount(), "优惠金额未设置");
        AssertUtil.notNull(grouponRules.getDiscountMember(), "达到优惠条件的人数未设置");
        AssertUtil.notNull(grouponRules.getExpireTime(), "过期时间未设置");
    }
}