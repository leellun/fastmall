package com.newland.mall.service;

import com.newland.mall.entity.GoodsSpecification;
import com.newland.mall.model.vo.wx.GoodsSpecificationVO;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 商品规格表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface GoodsSpecificationService extends IService<GoodsSpecification> {
    /**
     * 根据商品id获取商品规格
     * @param goodsId
     * @return
     */
    List<GoodsSpecification> listGoodsSpecifications(Long goodsId);

    /**
     * 添加商品规格
     * @param specification
     */
    void add(GoodsSpecification specification);

    /**
     * 获取规格
     * @param goodsId
     * @return
     */
    List<GoodsSpecificationVO> getSpecificationVoList(Long goodsId);
}