package com.newland.mall.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;

/**
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）
 * mall_goods_category_attr_relation
 * @author leell
 * @date 2023-06-08 13:44:15
 */
@Data
@Schema(name ="产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）")
public class GoodsCategoryAttrRelation implements Serializable {
    /**
     * 商品分类属性关联id
     */
    @Schema(name ="商品分类属性关联id")
    private Long id;

    /**
     * 商品id
     */
    @Schema(name ="商品id")
    private Long goodsCategoryId;

    /**
     * 属性id
     */
    @Schema(name ="属性id")
    private Long goodsAttrId;

    private static final long serialVersionUID = 1L;
}