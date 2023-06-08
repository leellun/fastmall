package com.newland.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 优惠券用户使用表
 * mall_coupon_user
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="优惠券用户使用表")
public class CouponUser extends BaseEntity implements Serializable {

    /**
     * 用户ID
     */
    @Schema(name ="用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /**
     * 优惠券ID
     */
    @Schema(name ="优惠券ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long couponId;

    /**
     * 使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架；
     * @see com.newland.mall.enums.CouponUserStatusEnum
     */
    @Schema(name ="使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架；")
    private Integer status;

    /**
     * 使用时间
     */
    @Schema(name ="使用时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime usedTime;

    /**
     * 有效期开始时间
     */
    @Schema(name ="有效期开始时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 有效期截至时间
     */
    @Schema(name ="有效期截至时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 订单ID
     */
    @Schema(name ="订单ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;

    private static final long serialVersionUID = 1L;
}