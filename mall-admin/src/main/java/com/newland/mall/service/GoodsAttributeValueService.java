package com.newland.mall.service;

import com.newland.mall.entity.GoodsAttributeValue;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 商品参数表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface GoodsAttributeValueService extends IService<GoodsAttributeValue> {
    /**
     * 根据商品id获取商品属性
     * @param goodsId
     * @return
     */
    List<GoodsAttributeValue> listGoodsAttributes(Long goodsId);

    /**
     * 添加
     * @param attribute
     */
    void add(GoodsAttributeValue attribute);

    /**
     * 删除商品
     * @param id
     */
    void delete(Long id);
}