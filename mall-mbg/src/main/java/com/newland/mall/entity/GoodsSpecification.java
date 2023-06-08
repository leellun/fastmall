package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品规格表
 * mall_goods_specification
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="商品规格表")
public class GoodsSpecification extends BaseEntity implements Serializable {

    /**
     * 商品表的商品ID
     */
    @Schema(name ="商品表的商品ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long goodsId;
    /**
     * 商品参数id
     */
    @Schema(name ="商品参数id")
    private Long goodsAttributeId;

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
     * 商品规格图片
     */
    @Schema(name ="商品规格图片")
    private String picUrl;

    private static final long serialVersionUID = 1L;
}