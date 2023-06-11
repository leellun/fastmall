package com.newland.mall.service;

import com.newland.mall.entity.GoodsSaleAttr;
import com.newland.mall.entity.GoodsSpec;
import com.newland.mall.model.vo.GoodsSaleAttrVo;
import com.newland.mybatis.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 商品规格表(销售) 服务类
 * @author leellun
 * @since 2023-06-11 15:11:54
 */
public interface GoodsSaleAttrService extends IService<GoodsSaleAttr> {
    /**
     * 保存销售属性
     * @param goodsId 商品id
     * @param goodsSaleAttrVos 销售属性
     * @return 规格表
     */
    List<GoodsSaleAttrVo> saveSaleAttr(Long goodsId, List<GoodsSaleAttrVo> goodsSaleAttrVos);

    /**
     * 保存销售属性
     * @param goodsId 商品id
     * @param goodsSaleAttrVos 销售属性
     * @return 规格表
     */
    List<GoodsSaleAttrVo> updateSaleAttr(Long goodsId, List<GoodsSaleAttrVo> goodsSaleAttrVos);
}