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
 * 团购活动表
 * mall_groupon
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="团购活动表")
public class Groupon  extends BaseEntity implements Serializable {

    /**
     * 关联的订单ID
     */
    @Schema(name ="关联的订单ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;

    /**
     * 如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID
     */
    @Schema(name ="如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long grouponId;

    /**
     * 团购规则ID，关联mall_groupon_rules表ID字段
     */
    @Schema(name ="团购规则ID，关联mall_groupon_rules表ID字段")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long rulesId;

    /**
     * 用户ID
     */
    @Schema(name ="用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /**
     * 团购分享图片地址
     */
    @Schema(name ="团购分享图片地址")
    private String shareUrl;

    /**
     * 开团用户ID
     */
    @Schema(name ="开团用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long creatorUserId;

    /**
     * 开团时间
     */
    @Schema(name ="开团时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creatorUserTime;

    /**
     * 团购活动状态，开团未支付则0，开团中则1，开团失败则2
     * @see com.newland.mall.enums.GrouponStatusEnum
     */
    @Schema(name ="团购活动状态，开团未支付则0，开团中则1，开团失败则2")
    private Integer status;


    private static final long serialVersionUID = 1L;
}