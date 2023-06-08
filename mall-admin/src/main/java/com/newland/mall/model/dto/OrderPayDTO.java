package com.newland.mall.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单收款
 * Author: leell
 * Date: 2023/3/19 20:56:56
 */
@Data
public class OrderPayDTO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;
    private BigDecimal newMoney;
}
