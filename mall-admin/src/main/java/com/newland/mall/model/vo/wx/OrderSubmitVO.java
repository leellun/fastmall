package com.newland.mall.model.vo.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 订单提交结果
 * Author: leell
 * Date: 2023/4/5 15:55:03
 */
@Data
public class OrderSubmitVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;
    private Boolean payed;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long grouponLinkId;
}
