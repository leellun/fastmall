package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.*;
import com.newland.mall.enums.GrouponStatusEnum;
import com.newland.mall.enums.OrderStatusEnum;
import com.newland.mall.express.ExpressService;
import com.newland.mall.express.dao.ExpressInfo;
import com.newland.mall.mapper.GrouponMapper;
import com.newland.mall.model.StatisticsEntity;
import com.newland.mall.model.vo.GrouponListItemVO;
import com.newland.mall.model.vo.UserVO;
import com.newland.mall.model.vo.wx.*;
import com.newland.mall.service.*;
import com.newland.mall.utils.AssertUtil;
import com.newland.mall.utils.OrderUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 团购活动表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class GrouponServiceImpl extends ServiceImpl<GrouponMapper, Groupon> implements GrouponService {
    @Autowired
    private GrouponRulesService grouponRulesService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderGoodsService orderGoodsService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ExpressService expressService;
    @Autowired
    private UserService userService;

    @Override
    public PageInfo<GrouponListItemVO> listGrouponList(Long grouponRuleId, PageEntity pageEntity) {
        PageInfo<Groupon> pageInfo = PageWrapper.page(pageEntity, () -> baseMapper.listByRulesId(grouponRuleId));
        List<GrouponListItemVO> groupons = new ArrayList<>();
        for (Groupon groupon : pageInfo.getList()) {
            GrouponListItemVO vo = new GrouponListItemVO();
            List<Groupon> subGrouponList = baseMapper.listSubGroupon(groupon.getId(), GrouponStatusEnum.STATUS_NONE.getKey());
            GrouponRules rules = grouponRulesService.getById(groupon.getRulesId());
            Goods goods = goodsService.getById(rules.getGoodsId());
            vo.setGoods(goods);
            vo.setGroupon(groupon);
            vo.setSubGroupons(subGrouponList);
            vo.setRules(rules);
            groupons.add(vo);
        }
        return PageWrapper.newPageInfo(pageInfo, groupons);
    }

    @Override
    public List<Groupon> listByRuleId(Long grouponRuleId) {
        return baseMapper.listByRulesId(grouponRuleId);
    }

    @Override
    public Groupon getByOrderId(Long orderId) {
        return baseMapper.getByOrderId(orderId);
    }

    @Override
    public GrouponDetailVO getDetail(Long userId, Long grouponId) {
        GrouponDetailVO vo = new GrouponDetailVO();
        Groupon groupon = baseMapper.selectByPrimaryKey(grouponId);
        AssertUtil.notNull(groupon, "团购活动不存在");
        AssertUtil.isTrue(groupon.getUserId().equals(userId), "操作异常");

        GrouponRules rules = grouponRulesService.getById(groupon.getRulesId());
        AssertUtil.notNull(rules, "团购规则不存在");

        // 订单信息
        Order order = orderService.getById(groupon.getOrderId());
        AssertUtil.notNull(order, "订单不存在");
        AssertUtil.isTrue(order.getUserId().equals(userId), "不是当前用户的订单");
        GrouponOrderVO orderVo = new GrouponOrderVO();
        orderVo.setId(order.getId());
        orderVo.setOrderSn(order.getOrderSn());
        orderVo.setCreateTime(order.getCreateTime());
        orderVo.setConsignee(order.getConsignee());
        orderVo.setMobile(order.getMobile());
        orderVo.setAddress(order.getAddress());
        orderVo.setGoodsPrice(order.getGoodsPrice());
        orderVo.setFreightPrice(order.getFreightPrice());
        orderVo.setActualPrice(order.getActualPrice());
        orderVo.setOrderStatusText(OrderUtil.orderStatusText(order));
        orderVo.setHandleOption(OrderUtil.build(order));
        orderVo.setExpCode(order.getShipChannel());
        orderVo.setExpNo(order.getShipSn());


        List<OrderGoods> orderGoodsList = orderGoodsService.listByOrderId(order.getId());

        List<GrouponOrderGoodsVO> orderGoodsVoList = new ArrayList<>();
        for (OrderGoods orderGoods : orderGoodsList) {
            GrouponOrderGoodsVO orderGoodsVo = new GrouponOrderGoodsVO();
            orderGoodsVo.setId(orderGoods.getId());
            orderGoodsVo.setOrderId(orderGoods.getOrderId());
            orderGoodsVo.setGoodsId(orderGoods.getGoodsId());
            orderGoodsVo.setGoodsName(orderGoods.getGoodsName());
            orderGoodsVo.setNumber(orderGoods.getNumber());
            orderGoodsVo.setRetailPrice(orderGoods.getPrice());
            orderGoodsVo.setPicUrl(orderGoods.getPicUrl());
            orderGoodsVo.setGoodsSpecificationValues(orderGoods.getSpecifications());
            orderGoodsVoList.add(orderGoodsVo);
        }
        vo.setOrderInfo(orderVo);
        vo.setOrderGoods(orderGoodsVoList);
        // 订单状态为已发货且物流信息不为空
        //"YTO", "800669400640887922"
        if (order.getOrderStatus().equals(OrderStatusEnum.STATUS_SHIP.getKey())) {
            ExpressInfo ei = expressService.getExpressInfo(order.getShipChannel(), order.getShipSn());
            vo.setExpressInfo(ei);
        }

        UserVO creator = userService.getUserVoByUserId(groupon.getCreatorUserId());
        List<UserVO> joiners = new ArrayList<>();
        joiners.add(creator);
        Long linkGrouponId;
        // 这是一个团购发起记录
        if (groupon.getGrouponId() == 0) {
            linkGrouponId = groupon.getId();
        } else {
            linkGrouponId = groupon.getGrouponId();

        }
        List<Groupon> groupons = baseMapper.listSubGroupon(linkGrouponId, GrouponStatusEnum.STATUS_NONE.getKey());

        UserVO joiner;
        for (Groupon grouponItem : groupons) {
            joiner = userService.getUserVoByUserId(grouponItem.getUserId());
            joiners.add(joiner);
        }
        vo.setLinkGrouponId(linkGrouponId);
        vo.setCreator(creator);
        vo.setJoiners(joiners);
        vo.setGroupon(groupon);
        vo.setRules(rules);
        return vo;
    }

    @Override
    public GrouponJoinVO getGroupJoin(Long grouponId) {
        GrouponJoinVO vo = new GrouponJoinVO();
        Groupon groupon = baseMapper.selectByPrimaryKey(grouponId);
        AssertUtil.notNull(groupon, "参团活动不存在");

        GrouponRules rules = grouponRulesService.getById(groupon.getRulesId());
        AssertUtil.notNull(rules, "参团规则不存在");

        Goods goods = goodsService.getById(rules.getGoodsId());
        AssertUtil.notNull(goods, "参团商品不存在");

        vo.setGroupon(groupon);
        vo.setGoods(goods);
        return vo;
    }

    @Override
    public StatisticsEntity<GrouponRelatedVO> getMyRelatedGroupon(Long userId, Integer showType) {
        List<Groupon> myGroupons = baseMapper.listRelated(userId, showType, GrouponStatusEnum.STATUS_NONE.getKey());

        List<GrouponRelatedVO> grouponVoList = new ArrayList<>(myGroupons.size());

        for (Groupon groupon : myGroupons) {
            Order order = orderService.getById(groupon.getOrderId());
            if (!order.getUserId().equals(userId)) {
                continue;
            }
            GrouponRules rules = grouponRulesService.getById(groupon.getRulesId());
            User creator = userService.getById(groupon.getCreatorUserId());

            GrouponRelatedVO vo = new GrouponRelatedVO();
            vo.setId(groupon.getId());
            vo.setGroupon(groupon);
            vo.setRules(rules);
            vo.setCreator(creator.getNickname());

            Long linkGrouponId;
            // 这是一个团购发起记录
            if (groupon.getGrouponId() == 0) {
                vo.setIsCreator(creator.getId().equals(userId));
                linkGrouponId = groupon.getId();
            } else {
                vo.setIsCreator(false);
                linkGrouponId = groupon.getGrouponId();
            }
            Long joinerCount = baseMapper.countByGroupIdAndStatus(linkGrouponId, GrouponStatusEnum.STATUS_NONE.getKey());
            vo.setJoinerCount((int) (joinerCount + 1));
            //填充订单信息
            vo.setOrderId(order.getId());
            vo.setOrderSn(order.getOrderSn());
            vo.setActualPrice(order.getActualPrice());
            vo.setOrderStatusText(OrderUtil.orderStatusText(order));

            List<OrderGoods> orderGoodsList = orderGoodsService.listByOrderId(order.getId());
            List<GrouponOrderGoodsVO> orderGoodsVoList = new ArrayList<>(orderGoodsList.size());
            for (OrderGoods orderGoods : orderGoodsList) {
                GrouponOrderGoodsVO orderGoodsVo = new GrouponOrderGoodsVO();
                orderGoodsVo.setId(orderGoods.getId());
                orderGoodsVo.setGoodsName(orderGoods.getGoodsName());
                orderGoodsVo.setNumber(orderGoods.getNumber());
                orderGoodsVo.setPicUrl(orderGoods.getPicUrl());
                orderGoodsVoList.add(orderGoodsVo);
            }
            vo.setGoodsList(orderGoodsVoList);
            grouponVoList.add(vo);
        }
        StatisticsEntity<GrouponRelatedVO> result = new StatisticsEntity<>();
        result.setTotal(grouponVoList.size());
        result.setList(grouponVoList);
        return result;
    }

    @Override
    public Long countGroupon(Long grouponId) {
        return baseMapper.countByGroupIdAndStatus(grouponId, GrouponStatusEnum.STATUS_NONE.getKey());
    }

    @Override
    public boolean hasJoin(Long userId, Long grouponId) {
        return baseMapper.countByUserIdAndGroupIdAndStatus(userId, grouponId, GrouponStatusEnum.STATUS_NONE.getKey()) > 0;
    }

    @Override
    public void createGroupon(Groupon groupon) {
        baseMapper.insert(groupon);
    }

    @Override
    public List<Groupon> listJoinRecord(Long grouponId) {
        return baseMapper.listJoinRecord(grouponId);
    }
}