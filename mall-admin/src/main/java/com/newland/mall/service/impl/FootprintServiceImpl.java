package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Footprint;
import com.newland.mall.entity.Goods;
import com.newland.mall.mapper.FootprintMapper;
import com.newland.mall.model.vo.wx.FootprintVO;
import com.newland.mall.service.FootprintService;
import com.newland.mall.service.GoodsService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户浏览足迹表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class FootprintServiceImpl extends ServiceImpl<FootprintMapper, Footprint> implements FootprintService {
    @Autowired
    private GoodsService goodsService;

    @Override
    public PageInfo<Footprint> listFootprints(Long userId, Long goodsId, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listByUserIdAndGoodsId(userId, goodsId));
    }

    @Override
    public void delete(Long userId, Long footprintId) {
        Footprint footprint = baseMapper.selectByPrimaryKey(footprintId);
        AssertUtil.notNull(footprint, "数据不存在");
        AssertUtil.isTrue(footprint.getUserId().equals(userId), "无权操作");
        baseMapper.deleteLogicalByPrimaryKey(footprintId);
    }

    @Override
    public PageInfo<FootprintVO> listMyFootprints(Long userId, PageEntity pageEntity) {
        pageEntity.setOrder("create_time");
        pageEntity.setDesc(true);
        PageInfo<Footprint> pageInfo = PageWrapper.page(pageEntity, () -> baseMapper.listByUserIdAndGoodsId(userId, null));
        List<Footprint> footprintList = pageInfo.getList();
        List<FootprintVO> footprintVoList = new ArrayList<>(footprintList.size());
        for (Footprint footprint : footprintList) {
            FootprintVO vo = new FootprintVO();
            vo.setId(footprint.getId());
            vo.setGoodsId(footprint.getGoodsId());
            vo.setCreateTime(footprint.getCreateTime());

            Goods goods = goodsService.getById(footprint.getGoodsId());
            vo.setName(goods.getName());
            vo.setBrief(goods.getBrief());
            vo.setPicUrl(goods.getPicUrl());
            vo.setRetailPrice(goods.getRetailPrice());

            footprintVoList.add(vo);
        }
        return PageWrapper.newPageInfo(pageInfo, footprintVoList);
    }

    @Override
    public void addFootprint(Footprint footprint) {
        baseMapper.insertSelective(footprint);
    }
}