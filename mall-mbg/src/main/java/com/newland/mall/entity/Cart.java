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
 * 购物车商品表
 * mall_cart
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="购物车商品表")
public class Cart extends BaseEntity implements Serializable {

    /**
     * 用户表的用户ID
     */
    @Schema(name ="用户表的用户ID")
    private Long userId;

    /**
     * 商品表的商品ID
     */
    @Schema(name ="商品表的商品ID")
    private Long goodsId;

    /**
     * 商品名称
     */
    @Schema(name ="商品名称")
    private String goodsName;

    /**
     * 商品货品表的货品ID
     */
    @Schema(name ="商品货品表的货品ID")
    private Long productId;

    /**
     * 商品编号
     */
    @Schema(name ="商品编号")
    private String productSn;

    /**
     * 1:有效 2：商品已下架 3：sku产品无效
     */
    @Schema(name ="1:有效 2：商品已下架 3：sku产品无效")
    private Integer status;

    /**
     * 商品货品的价格
     */
    @Schema(name ="商品货品的价格")
    private BigDecimal price;

    /**
     * 商品货品的数量
     */
    @Schema(name ="商品货品的数量")
    private Integer number;

    /**
     * 购物车中商品是否选择状态
     */
    @Schema(name ="购物车中商品是否选择状态")
    private Integer checked;

    /**
     * 商品图片或者商品货品图片
     */
    @Schema(name ="商品图片或者商品货品图片")
    private String picUrl;

    private static final long serialVersionUID = 1L;
}