package com.newland.mall.model.vo.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.utils.OrderHandleOption;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 团购订单信息
 * Author: leell
 * Date: 2023/4/5 09:35:56
 */
@Data
public class GrouponOrderVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String orderSn;
    private LocalDateTime createTime;
    private String consignee;
    private String mobile;
    private String address;
    private String orderStatusText;
    private String expCode;
    private String expNo;
    private BigDecimal goodsPrice;
    private BigDecimal freightPrice;
    private BigDecimal actualPrice;
    private OrderHandleOption handleOption;
}
