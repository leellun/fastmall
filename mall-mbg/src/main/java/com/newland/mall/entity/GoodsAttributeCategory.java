package com.newland.mall.entity;

import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 产品属性分类表
 * mall_goods_attribute_category
 * @author leell
 * @date 2023-06-04 21:50:54
 */
@Data
@Schema(name ="产品属性分类表")
public class GoodsAttributeCategory extends BaseEntity implements Serializable {

    /**
     * 属性分类
     */
    @Schema(name ="属性分类")
    private String name;

    /**
     * 属性数量
     */
    @Schema(name ="属性数量")
    private Integer attributeCount;

    /**
     * 参数数量
     */
    @Schema(name ="参数数量")
    private Integer paramCount;

    private static final long serialVersionUID = 1L;
}