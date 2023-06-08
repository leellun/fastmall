package com.newland.mall.model.dto.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 订单提交
 * Author: leell
 * Date: 2023/4/5 15:55:03
 */
@Data
public class OrderSubmitDTO {
    @NotNull(message = "购物车id不能为空")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long cartId;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long addressId;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long couponId;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userCouponId;
    private String message;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long grouponRulesId;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long grouponLinkId;
}
