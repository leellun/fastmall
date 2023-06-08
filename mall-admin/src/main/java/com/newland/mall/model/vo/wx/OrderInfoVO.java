package com.newland.mall.model.vo.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.utils.OrderHandleOption;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单详情信息vo
 * Author: leell
 * Date: 2023/4/5 15:12:24
 */
@Data
public class OrderInfoVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String orderSn;
    private String message;
    private LocalDateTime createTime;
    private String consignee;
    private String mobile;
    private String address;
    private String orderStatusText;
    private String expCode;
    private String expNo;
    private String expName;
    private Integer aftersaleStatus;
    private BigDecimal goodsPrice;
    private BigDecimal freightPrice;
    private BigDecimal actualPrice;
    private BigDecimal couponPrice;
    private OrderHandleOption handleOption;
}
