package com.newland.mall.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单退款
 * Author: leell
 * Date: 2023/3/19 19:37:27
 */
@Data
public class OrderRefundDTO {
    /**
     * 订单
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;
    /**
     * 退款金额
     */
    private BigDecimal refundMoney;
}
