package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品参数表
 * mall_goods_attr_value
 * @author leell
 * @date 2023-06-08 13:44:15
 */
@Data
@Schema(name ="商品参数表")
public class GoodsAttrValue extends BaseEntity implements Serializable {

    /**
     * 商品表的商品ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    @Schema(name ="商品表的商品ID")
    private Long goodsId;

    /**
     * 商品参数id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    @Schema(name ="商品参数id")
    private Long goodsAttrId;

    /**
     * 商品参数名称
     */
    @Schema(name ="商品参数名称")
    private String name;

    /**
     * 商品参数值
     */
    @Schema(name ="商品参数值")
    private String value;

    /**
     * 排序
     */
    @Schema(name ="排序")
    private Integer attributeSort;

    private static final long serialVersionUID = 1L;
}