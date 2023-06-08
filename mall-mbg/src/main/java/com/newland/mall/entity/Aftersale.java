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
 * 售后表
 * mall_aftersale
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="售后表")
public class Aftersale extends BaseEntity implements Serializable {

    /**
     * 售后编号
     */
    @Schema(name ="售后编号")
    private String aftersaleSn;

    /**
     * 订单ID
     */
    @Schema(name ="订单ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;

    /**
     * 用户ID
     */
    @Schema(name ="用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /**
     * 售后类型，0是未收货退款，1是已收货（无需退货）退款，2用户退货退款
     * @see com.newland.mall.enums.AftersaleTypeEnum
     */
    @Schema(name ="售后类型，0是未收货退款，1是已收货（无需退货）退款，2用户退货退款")
    private Integer type;

    /**
     * 退款原因
     */
    @Schema(name ="退款原因")
    private String reason;

    /**
     * 退款金额
     */
    @Schema(name ="退款金额")
    private BigDecimal amount;

    /**
     * 退款凭证图片链接数组
     */
    @Schema(name ="退款凭证图片链接数组")
    private String[] pictures;

    /**
     * 退款说明
     */
    @Schema(name ="退款说明")
    private String comment;

    /**
     * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
     * @see com.newland.mall.enums.AftersaleStatusEnum
     */
    @Schema(name ="售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消")
    private Integer status;

    /**
     * 管理员操作时间
     */
    @Schema(name ="管理员操作时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime handleTime;

    private static final long serialVersionUID = 1L;
}