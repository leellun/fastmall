package com.newland.mall.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 订单发货
 * Author: leell
 * Date: 2023/3/19 20:51:00
 */
@Data
public class OrderShipDTO {
    /**
     * 订单id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;
    /**
     * 发货码
     */
    private String shipSn;
    /**
     * 发货通道
     */
    private String shipChannel;
}
