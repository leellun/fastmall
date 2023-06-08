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
 * 团购规则表
 * mall_groupon_rules
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="团购规则表")
public class GrouponRules  extends BaseEntity implements Serializable {

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
     * 商品图片或者商品货品图片
     */
    @Schema(name ="商品图片或者商品货品图片")
    private String picUrl;

    /**
     * 优惠金额
     */
    @Schema(name ="优惠金额")
    private BigDecimal discount;

    /**
     * 达到优惠条件的人数
     */
    @Schema(name ="达到优惠条件的人数")
    private Integer discountMember;

    /**
     * 团购过期时间
     */
    @Schema(name ="团购过期时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireTime;

    /**
     * 团购规则状态，正常上线则0，到期自动下线则1，管理手动下线则2
     * @see com.newland.mall.enums.GrouponRulesStatusEnum
     */
    @Schema(name ="团购规则状态，正常上线则0，到期自动下线则1，管理手动下线则2")
    private Integer status;


    private static final long serialVersionUID = 1L;
}