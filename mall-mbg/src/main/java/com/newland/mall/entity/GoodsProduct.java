package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品货品表
 * mall_goods_product
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="商品货品表")
public class GoodsProduct  extends BaseEntity implements Serializable {

    /**
     * 商品表的商品ID
     */
    @Schema(name ="商品表的商品ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long goodsId;

    /**
     * 商品规格值列表，采用JSON数组格式
     */
    @Schema(name ="商品规格值列表，采用JSON数组格式")
    private String specifications;

    /**
     * 商品货品价格
     */
    @Schema(name ="商品货品价格")
    private BigDecimal price;

    /**
     * 商品货品数量
     */
    @Schema(name ="商品货品数量")
    private Integer number;

    /**
     * 商品货品图片
     */
    @Schema(name ="商品货品图片")
    private String url;

    private static final long serialVersionUID = 1L;
}