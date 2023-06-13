package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品货品表-规格 中间表
 * mall_goods_product_spec_relation
 * @author leell
 * @date 2023-06-08 13:44:15
 */
@Data
@Schema(name ="商品货品表-规格 中间表")
public class GoodsProductSpecRelation extends BaseEntity implements Serializable {

    /**
     * 规格ID
     */
    @Schema(name ="规格ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long specId;

    /**
     * product id
     */
    @Schema(name ="product id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long productId;

    /**
     * 商品规格名称
     */
    @Schema(name ="商品规格名称")
    private String name;

    /**
     * 商品规格值
     */
    @Schema(name ="商品规格值")
    private String value;

    /**
     * 顺序
     */
    @Schema(name ="顺序")
    private Integer itemSort;

    /**
     * 商品参数id
     */
    @Schema(name ="商品参数id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long goodsAttrId;

    /**
     * 值id
     */
    @Schema(name ="值id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long goodsValueId;

    private static final long serialVersionUID = 1L;
}