package com.newland.mall.model.vo.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.utils.OrderHandleOption;
import com.newland.mall.utils.OrderUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 团购订单信息
 * Author: leell
 * Date: 2023/4/5 09:35:56
 */
@Data
public class OrderVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String orderSn;
    private BigDecimal actualPrice;
    private String orderStatusText;
    private OrderHandleOption handleOption;
    private Integer aftersaleStatus;
    private Boolean isGroupin;
    private List<OrderGoodsVO> goodsList;
}
