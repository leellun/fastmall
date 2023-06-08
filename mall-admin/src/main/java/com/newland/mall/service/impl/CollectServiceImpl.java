package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Collect;
import com.newland.mall.entity.Goods;
import com.newland.mall.entity.Topic;
import com.newland.mall.mapper.CollectMapper;
import com.newland.mall.mapper.GoodsMapper;
import com.newland.mall.model.dto.wx.ValueCollectVO;
import com.newland.mall.model.vo.wx.CollectVO;
import com.newland.mall.service.CollectService;
import com.newland.mall.service.TopicService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private TopicService topicService;

    @Override
    public PageInfo<Collect> listCollect(Long userId, Long valueId, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listByUserIdAndValueId(userId, valueId));
    }

    @Override
    public PageInfo<CollectVO> listCollect(Long userId, Integer type, PageEntity pageEntity) {
        PageInfo<Collect> pageInfo = PageWrapper.page(pageEntity, () -> baseMapper.listByUserIdAndValueId(userId, null));
        List<CollectVO> collects = new ArrayList<>(pageInfo.getList().size());
        for (Collect collect : pageInfo.getList()) {
            CollectVO collectVO = new CollectVO();
            collectVO.setId(collect.getId());
            collectVO.setType(collect.getType());
            collectVO.setValueId(collect.getValueId());
            if (type == (byte) 0) {
                //查询商品信息
                Goods goods = goodsMapper.selectByPrimaryKey(collect.getValueId());
                collectVO.setName(goods.getName());
                collectVO.setBrief(goods.getBrief());
                collectVO.setPicUrl(goods.getPicUrl());
                collectVO.setRetailPrice(goods.getRetailPrice());
            } else {
                //查询专题信息
                Topic topic = topicService.getById(collect.getValueId());
                collectVO.setTitle(topic.getTitle());
                collectVO.setSubtitle(topic.getSubtitle());
                collectVO.setPrice(topic.getPrice());
                collectVO.setPicUrl(topic.getPicUrl());
            }
            collects.add(collectVO);
        }
        PageInfo<CollectVO> result = PageWrapper.newPageInfo(pageInfo, collects);
        return result;
    }

    @Override
    public Boolean addOrDelete(Long userId, ValueCollectVO valueCollectVO) {
        Integer type = valueCollectVO.getType();
        Long valueId = valueCollectVO.getValueId();

        Collect collect = baseMapper.getByTypeAndValue(userId, type, valueId);
        if (collect != null) {
            baseMapper.deleteLogicalByPrimaryKey(collect.getId());
            return false;
        } else {
            collect = new Collect();
            collect.setUserId(userId);
            collect.setValueId(valueId);
            collect.setType(type);
            baseMapper.insertSelective(collect);
            return true;
        }
    }

    @Override
    public int count(Long userId, Integer type, Long valueId) {
        return baseMapper.countByUserIdAndTypeAndValueId(userId,type,valueId).intValue();
    }
}