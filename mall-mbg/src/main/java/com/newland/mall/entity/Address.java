package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 收货地址表
 * mall_address
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="收货地址表")
public class Address extends BaseEntity implements Serializable {

    /**
     * 收货人名称
     */
    @Schema(name ="收货人名称")
    private String name;

    /**
     * 用户表的用户ID
     */
    @Schema(name ="用户表的用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /**
     * 行政区域表的省ID
     */
    @Schema(name ="行政区域表的省ID")
    private String province;

    /**
     * 行政区域表的市ID
     */
    @Schema(name ="行政区域表的市ID")
    private String city;

    /**
     * 行政区域表的区县ID
     */
    @Schema(name ="行政区域表的区县ID")
    private String county;

    /**
     * 详细收货地址
     */
    @Schema(name ="详细收货地址")
    private String addressDetail;

    /**
     * 地区编码
     */
    @Schema(name ="地区编码")
    private String areaCode;

    /**
     * 邮政编码
     */
    @Schema(name ="邮政编码")
    private String postalCode;

    /**
     * 手机号码
     */
    @Schema(name ="手机号码")
    private String tel;

    /**
     * 是否默认地址
     */
    @Schema(name ="是否默认地址")
    private Integer isDefault;

    private static final long serialVersionUID = 1L;
}