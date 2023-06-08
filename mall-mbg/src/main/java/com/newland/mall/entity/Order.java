package com.newland.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单表
 * mall_order
 *
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name = "订单表")
public class Order extends BaseEntity implements Serializable {

    /**
     * 用户表的用户ID
     */
    @Schema(name = "用户表的用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /**
     * 订单编号
     */
    @Schema(name = "订单编号")
    private String orderSn;

    /**
     * 订单状态
     *
     * @see com.newland.mall.enums.OrderStatusEnum
     */
    @Schema(name = "订单状态")
    private Integer orderStatus;

    /**
     * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
     *
     * @see com.newland.mall.enums.AftersaleStatusEnum
     */
    @Schema(name = "售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消")
    private Integer aftersaleStatus;

    /**
     * 收货人名称
     */
    @Schema(name = "收货人名称")
    private String consignee;

    /**
     * 收货人手机号
     */
    @Schema(name = "收货人手机号")
    private String mobile;

    /**
     * 收货具体地址
     */
    @Schema(name = "收货具体地址")
    private String address;

    /**
     * 用户订单留言
     */
    @Schema(name = "用户订单留言")
    private String message;

    /**
     * 商品总费用
     */
    @Schema(name = "商品总费用")
    private BigDecimal goodsPrice;

    /**
     * 配送费用
     */
    @Schema(name = "配送费用")
    private BigDecimal freightPrice;

    /**
     * 优惠券减免
     */
    @Schema(name = "优惠券减免")
    private BigDecimal couponPrice;

    /**
     * 用户积分减免
     */
    @Schema(name = "用户积分减免")
    private BigDecimal integralPrice;

    /**
     * 团购优惠价减免
     */
    @Schema(name = "团购优惠价减免")
    private BigDecimal grouponPrice;

    /**
     * 订单费用， = goods_price + freight_price - coupon_price
     */
    @Schema(name = "订单费用， = goods_price + freight_price - coupon_price")
    private BigDecimal orderPrice;

    /**
     * 实付费用， = order_price - integral_price
     */
    @Schema(name = "实付费用， = order_price - integral_price")
    private BigDecimal actualPrice;

    /**
     * 微信付款编号
     */
    @Schema(name = "微信付款编号")
    private String payId;

    /**
     * 微信付款时间
     */
    @Schema(name = "微信付款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;

    /**
     * 发货编号
     */
    @Schema(name = "发货编号")
    private String shipSn;

    /**
     * 发货快递公司
     */
    @Schema(name = "发货快递公司")
    private String shipChannel;

    /**
     * 发货开始时间
     */
    @Schema(name = "发货开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime shipTime;

    /**
     * 实际退款金额，（有可能退款金额小于实际支付金额）
     */
    @Schema(name = "实际退款金额，（有可能退款金额小于实际支付金额）")
    private BigDecimal refundAmount;

    /**
     * 退款方式
     */
    @Schema(name = "退款方式")
    private String refundType;

    /**
     * 退款备注
     */
    @Schema(name = "退款备注")
    private String refundContent;

    /**
     * 退款时间
     */
    @Schema(name = "退款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime refundTime;

    /**
     * 用户确认收货时间
     */
    @Schema(name = "用户确认收货时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime confirmTime;

    /**
     * 待评价订单商品数量
     */
    @Schema(name = "待评价订单商品数量")
    private Integer comments;

    /**
     * 订单关闭时间
     */
    @Schema(name = "订单关闭时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private static final long serialVersionUID = 1L;
}