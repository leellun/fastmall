package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品参数表
 * mall_goods_attribute_value
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="商品参数表")
public class GoodsAttributeValue extends BaseEntity implements Serializable {

    /**
     * 商品表的商品ID
     */
    @Schema(name ="商品表的商品ID")
    private Long goodsId;

    /**
     * 商品参数id
     */
    @Schema(name ="商品参数id")
    private Long goodsAttributeId;

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
}