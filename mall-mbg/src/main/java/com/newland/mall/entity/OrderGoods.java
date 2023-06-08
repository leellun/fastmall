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
 * 订单商品表
 * mall_order_goods
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="订单商品表")
public class OrderGoods  extends BaseEntity implements Serializable {

    /**
     * 订单表的订单ID
     */
    @Schema(name ="订单表的订单ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;

    /**
     * 商品表的商品ID
     */
    @Schema(name ="商品表的商品ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long goodsId;

    /**
     * 商品名称
     */
    @Schema(name ="商品名称")
    private String goodsName;

    /**
     * 商品编号
     */
    @Schema(name ="商品编号")
    private String goodsSn;

    /**
     * 商品货品表的货品ID
     */
    @Schema(name ="商品货品表的货品ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long productId;

    /**
     * 商品货品的购买数量
     */
    @Schema(name ="商品货品的购买数量")
    private Integer number;

    /**
     * 商品货品的售价
     */
    @Schema(name ="商品货品的售价")
    private BigDecimal price;

    /**
     * 商品货品的规格列表
     */
    @Schema(name ="商品货品的规格列表")
    private String specifications;

    /**
     * 商品货品图片或者商品图片
     */
    @Schema(name ="商品货品图片或者商品图片")
    private String picUrl;

    /**
     * 订单商品评论，如果是-1，则超期不能评价；如果是0，则可以评价；如果其他值，则是comment表里面的评论ID。
     */
    @Schema(name ="订单商品评论，如果是-1，则超期不能评价；如果是0，则可以评价；如果其他值，则是comment表里面的评论ID。")
    private Long comment;

    private static final long serialVersionUID = 1L;
}