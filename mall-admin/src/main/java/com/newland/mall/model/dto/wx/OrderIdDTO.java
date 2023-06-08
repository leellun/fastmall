package com.newland.mall.model.dto.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 订单id dto
 * Author: leell
 * Date: 2023/4/9 00:14:52
 */
@Data
public class OrderIdDTO {
    @NotNull(message = "订单id不存在")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;
}
