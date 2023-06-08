package com.newland.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券信息及规则表
 * mall_coupon
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="优惠券信息及规则表")
public class Coupon extends BaseEntity implements Serializable {

    /**
     * 优惠券名称
     */
    @Schema(name ="优惠券名称")
    private String name;

    /**
     * 优惠券介绍，通常是显示优惠券使用限制文字
     */
    @Schema(name ="优惠券介绍，通常是显示优惠券使用限制文字")
    private String description;

    /**
     * 优惠券标签，例如新人专用
     */
    @Schema(name ="优惠券标签，例如新人专用")
    private String tag;

    /**
     * 优惠券数量，如果是0，则是无限量
     */
    @Schema(name ="优惠券数量，如果是0，则是无限量")
    private Integer total;

    /**
     * 优惠金额，
     */
    @Schema(name ="优惠金额，")
    private BigDecimal discount;

    /**
     * 最少消费金额才能使用优惠券。
     */
    @Schema(name ="最少消费金额才能使用优惠券。")
    private BigDecimal min;

    /**
     * 用户领券限制数量，如果是0，则是不限制；默认是1，限领一张.
     */
    @Schema(name ="用户领券限制数量，如果是0，则是不限制；默认是1，限领一张.")
    private Integer limitCount;

    /**
     * 优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换；
     * @see com.newland.mall.enums.CouponTypeEnum
     */
    @Schema(name ="优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换；")
    private Integer type;

    /**
     * 优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。
     * @see com.newland.mall.enums.CouponStatusEnum
     */
    @Schema(name ="优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。")
    private Integer status;

    /**
     * 商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。
     * @see com.newland.mall.enums.CouponGoodsTypeEnum
     */
    @Schema(name ="商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。")
    private Integer goodsType;

    /**
     * 商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。
     */
    @Schema(name ="商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。")
    private Long[] goodsValue;

    /**
     * 优惠券兑换码
     */
    @Schema(name ="优惠券兑换码")
    private String code;

    /**
     * 有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期；
     * @see com.newland.mall.enums.CouponTimeTypeEnum
     */
    @Schema(name ="有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期；")
    private Integer timeType;

    /**
     * 基于领取时间的有效天数days。
     */
    @Schema(name ="基于领取时间的有效天数days。")
    private Integer days;

    /**
     * 使用券开始时间
     */
    @Schema(name ="使用券开始时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 使用券截至时间
     */
    @Schema(name ="使用券截至时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;


    private static final long serialVersionUID = 1L;
}