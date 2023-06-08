package com.newland.mall.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 订单回复
 * Author: leell
 * Date: 2023/3/19 21:00:48
 */
@Data
public class OrderReplyDTO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long commentId;
    /**
     * 内容
     */
    private String content;
}
