package com.newland.mall.service.impl;

import com.newland.mall.entity.GoodsProduct;
import com.newland.mall.mapper.GoodsProductMapper;
import com.newland.mall.service.GoodsProductService;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品货品表 服务实现类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class GoodsProductServiceImpl extends ServiceImpl<GoodsProductMapper, GoodsProduct> implements GoodsProductService {
    @Override
    public Integer addStock(Long productId, Integer number) {
        return baseMapper.updateStock(productId,number);
    }

    @Override
    public List<GoodsProduct> listGoodsProducts(Long goodsId) {
        return baseMapper.listByGoodsId(goodsId);
    }

    @Override
    public void add(GoodsProduct product) {
        baseMapper.insert(product);
    }

    @Override
    public Long count() {
        return baseMapper.count();
    }

    @Override
    public int reduceStock(Long productId, Integer number) {
        return baseMapper.reduceStock(productId, number);
    }

}