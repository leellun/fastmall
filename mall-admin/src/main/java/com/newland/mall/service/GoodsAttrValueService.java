package com.newland.mall.service;

import com.newland.mall.entity.GoodsAttrValue;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 商品参数表 服务类
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
public interface GoodsAttrValueService extends IService<GoodsAttrValue> {

    /**
     * 根据商品id获取商品属性
     * @param goodsId
     * @return
     */
    List<GoodsAttrValue> listGoodsAttributes(Long goodsId);

    /**
     * 添加
     * @param attribute
     */
    void add(GoodsAttrValue attribute);

    /**
     * 删除商品
     * @param id
     */
    void delete(Long id);

    /**
     * 更新商品属性
     * @param goodsId 商品id
     * @param attributes 属性列表
     */
    void saveAttributes(Long goodsId, List<GoodsAttrValue> attributes);
    /**
     * 更新商品属性
     * @param goodsId 商品id
     * @param attributes 属性列表
     */
    void updateAttributes(Long goodsId, List<GoodsAttrValue> attributes);
}