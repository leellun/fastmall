package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Goods;
import com.newland.mall.entity.Issue;
import com.newland.mall.entity.Notice;
import com.newland.mall.entity.Topic;
import com.newland.mall.enumeration.BasicEnum;
import com.newland.mall.mapper.GoodsMapper;
import com.newland.mall.mapper.TopicMapper;
import com.newland.mall.model.vo.TopicVO;
import com.newland.mall.service.GoodsService;
import com.newland.mall.service.TopicService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专题表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageInfo<Topic> listTopicPage(String title, String subtitle, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listByTitleAndSubtitle(title, subtitle));
    }

    @Override
    public TopicVO get(Long id) {
        Topic dbTopic = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(dbTopic, "数据不存在");
        TopicVO vo = new TopicVO();
        Long[] goodsIds = dbTopic.getGoods();
        List<Goods> goodsList = null;
        if (goodsIds == null || goodsIds.length == 0) {
            goodsList = new ArrayList<>();
        } else {
            goodsList = goodsMapper.listByGoodsIdsAndOnSale(goodsIds, BasicEnum.YES.getKey());
        }
        vo.setTopic(dbTopic);
        vo.setGoodsList(goodsList);
        return vo;
    }

    @Override
    public void add(Topic topic) {
        validate(topic);
        baseMapper.insert(topic);
    }

    @Override
    public void update(Topic topic) {
        validate(topic);
        Topic dbTopic = baseMapper.selectByPrimaryKey(topic.getId());
        AssertUtil.notNull(dbTopic, "数据不存在");
        baseMapper.updateByPrimaryKeySelective(topic);
    }

    @Override
    public void delete(Long id) {
        Topic t = new Topic();
        t.setId(id);
        t.setDeleted(1);
        AssertUtil.isTrue(baseMapper.updateByPrimaryKeySelective(t) > 0, "删除失败");
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        baseMapper.deleteByIds(ids);
    }

    @Override
    public List<Topic> queryList(Integer pageSize) {
        return PageWrapper.list(1, pageSize, () -> baseMapper.listByTitleAndSubtitle(null, null));
    }

    @Override
    public List<Topic> listRelatedToptics(Long id, int offset, int limit) {
        Topic topic = baseMapper.selectByPrimaryKey(id);
        if (topic == null) {
            return PageWrapper.list(1, limit, () -> baseMapper.listByTitleAndSubtitle(null, null));
        } else {
            return PageWrapper.list(1, limit, () -> baseMapper.listByNotId(id));
        }
    }

    private void validate(Topic topic) {
        AssertUtil.isNotTrue(StringUtils.isEmpty(topic.getTitle()), "标题不能为空");
        AssertUtil.isNotTrue(StringUtils.isEmpty(topic.getContent()), "内容不能为空");
        AssertUtil.notNull(topic.getPrice(), "价格未设置");
    }
}