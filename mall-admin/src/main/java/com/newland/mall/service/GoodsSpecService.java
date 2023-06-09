package com.newland.mall.service;

import com.newland.mall.entity.GoodsSpec;
import com.newland.mall.model.vo.wx.GoodsSpecificationVO;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 商品规格表(销售) 服务类
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
public interface GoodsSpecService extends IService<GoodsSpec> {
    /**
     * 根据商品id获取商品规格
     * @param goodsId
     * @return
     */
    List<GoodsSpec> listGoodsSpecifications(Long goodsId);

    /**
     * 添加商品规格
     * @param specification
     */
    void add(GoodsSpec specification);

    /**
     * 获取规格
     * @param goodsId
     * @return
     */
    List<GoodsSpecificationVO> getSpecificationVoList(Long goodsId);
}