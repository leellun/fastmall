package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）
 * mall_goods_category_attr_relation
 * @author leell
 * @date 2023-06-08 20:25:46
 */
@Data
@Schema(name ="产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）")
public class GoodsCategoryAttrRelation extends BaseEntity implements Serializable {

    /**
     * 商品id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    @Schema(name ="商品id")
    private Long goodsCategoryId;

    /**
     * 属性id
     */
    @Schema(name ="属性id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long goodsAttrId;

    private static final long serialVersionUID = 1L;
}