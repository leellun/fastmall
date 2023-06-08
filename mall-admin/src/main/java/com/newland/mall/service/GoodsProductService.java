package com.newland.mall.service;

import com.newland.mall.entity.GoodsProduct;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 商品货品表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface GoodsProductService extends IService<GoodsProduct> {
    /**
     * 修改库存
     * @param productId 商品id
     * @param number 计数
     */
    Integer addStock(Long productId, Integer number);

    /**
     * 根据商品id获取获取产品信息
     * @param goodsId
     * @return
     */
    List<GoodsProduct> listGoodsProducts(Long goodsId);

    /**
     * 添加货品
     * @param product
     */
    void add(GoodsProduct product);

    /**
     * 货品数
     * @return
     */
    Long count();

    /**
     * 减少库存
     * @param productId
     * @param number
     * @return
     */
    int reduceStock(Long productId, Integer number);
}