package com.newland.mall.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;

/**
 * 产品的分类和属性组的关系表，用于设置分类筛选条件（只支持一级分类）
 * mall_goods_group_attr_relation
 * @author leell
 * @date 2023-06-08 13:44:15
 */
@Data
@Schema(name ="产品的分类和属性组的关系表，用于设置分类筛选条件（只支持一级分类）")
public class GoodsGroupAttrRelation implements Serializable {
    /**
     * 商品分类属性组关联id
     */
    @Schema(name ="商品分类属性组关联id")
    private Long id;

    /**
     * 分类id
     */
    @Schema(name ="分类id")
    private Long goodsCategoryId;

    /**
     * 分组id
     */
    @Schema(name ="分组id")
    private Long goodsGroupId;

    private static final long serialVersionUID = 1L;
}