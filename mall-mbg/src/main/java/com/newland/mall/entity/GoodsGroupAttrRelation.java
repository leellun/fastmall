package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 产品的分类和属性组的关系表，用于设置分类筛选条件（只支持一级分类）
 * mall_goods_group_attr_relation
 * @author leell
 * @date 2023-06-08 20:25:46
 */
@Data
@Schema(name ="产品的分类和属性组的关系表，用于设置分类筛选条件（只支持一级分类）")
public class GoodsGroupAttrRelation extends BaseEntity implements Serializable {

    /**
     * 属性id
     */
    @Schema(name ="属性id")
    private Long goodsAttrId;

    /**
     * 分组id
     */
    @Schema(name ="分组id")
    private Long goodsGroupId;

    /**
     * 分类id
     */
    @Schema(name ="分类id")
    private Long goodsCategoryId;


    private static final long serialVersionUID = 1L;
}