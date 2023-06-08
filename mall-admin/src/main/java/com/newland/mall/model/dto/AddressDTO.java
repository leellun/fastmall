package com.newland.mall.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 地址
 * Author: leell
 * Date: 2023/4/2 20:02:04
 */
@Data
@Schema(name ="收货地址表")
public class AddressDTO {
    @Schema(description = "ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 收货人名称
     */
    @NotEmpty(message = "收货人不能为空")
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
    @NotEmpty(message = "省不能为空")
    @Schema(name ="行政区域表的省ID")
    private String province;

    /**
     * 行政区域表的市ID
     */
    @NotEmpty(message = "城市不能为空")
    @Schema(name ="行政区域表的市ID")
    private String city;

    /**
     * 行政区域表的区县ID
     */
    @NotEmpty(message = "区县不能为空")
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
    @NotEmpty(message = "手机号码不能为空")
    @Pattern(regexp = "^[1]\\d{10}$",message = "手机号不正确")
    @Schema(name ="手机号码")
    private String tel;

    /**
     * 是否默认地址
     */
    @Schema(name ="是否默认地址")
    private Integer isDefault;
}
