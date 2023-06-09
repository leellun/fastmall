package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 产品属性分类表
 * mall_goods_group
 * @author leell
 * @date 2023-06-08 13:44:15
 */
@Data
@Schema(name ="产品属性分类表")
public class GoodsGroup extends BaseEntity implements Serializable {

    /**
     * 属性分类
     */
    @Schema(name ="属性分类")
    private String name;

    /**
     * 分类id
     */
    @Schema(name ="分类id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long categoryId;

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