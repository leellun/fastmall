package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.GoodsAttr;
import com.newland.mall.entity.GoodsValue;
import com.newland.mall.mapper.GoodsValueMapper;
import com.newland.mall.service.GoodsAttrService;
import com.newland.mall.service.GoodsValueService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品参数表 服务实现类
 *
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@Service
public class GoodsValueServiceImpl extends ServiceImpl<GoodsValueMapper, GoodsValue> implements GoodsValueService {
    @Autowired
    private GoodsAttrService goodsAttrService;

    @Override
    public PageInfo<GoodsValue> getList(Long goodsAttrId, Integer pageNo, Integer pageSize) {
        return PageWrapper.page(PageEntity.page(pageNo, pageSize), () -> baseMapper.listByAttrId(goodsAttrId));
    }

    @Override
    public void create(GoodsValue goodsValue) {
        GoodsAttr goodsAttr = goodsAttrService.getById(goodsValue.getGoodsAttrId());
        goodsValue.setName(goodsAttr.getName());
        baseMapper.insertSelective(goodsValue);
    }

    @Override
    public void update(GoodsValue goodsValue) {
        baseMapper.updateByPrimaryKeySelective(goodsValue);
    }

    @Override
    public void delete(List<Long> ids) {
        List<GoodsValue> goodsValues = ids.stream().map(id -> {
            GoodsValue goodsValue = new GoodsValue();
            goodsValue.setId(id);
            goodsValue.setDeleted(1);
            return goodsValue;
        }).collect(Collectors.toList());
        this.updateBatch(goodsValues);
    }
}