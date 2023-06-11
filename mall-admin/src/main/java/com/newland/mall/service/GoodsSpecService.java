package com.newland.mall.service;

import com.newland.mall.entity.GoodsSpec;
import com.newland.mall.model.vo.wx.GoodsSpecificationVO;
import com.newland.mybatis.service.IService;

import java.util.List;
import java.util.Map;

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

    /**
     * 保存商品规格
     * @param goodsId 商品id
     * @param specifications 规格
     * @return 规格对应
     */
    Map<String, GoodsSpec> saveGoodsSpecs(Long goodsId, List<GoodsSpec> specifications);
    /**
     * 保存商品规格
     * @param goodsId 商品id
     * @param specifications 规格
     * @return 规格对应
     */
    Map<String, GoodsSpec> updateGoodsSpecs(Long goodsId, List<GoodsSpec> specifications);
}