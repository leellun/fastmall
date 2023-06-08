package com.newland.mall.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单统计
 * Author: leell
 * Date: 2023/3/19 23:48:21
 */
@Data
public class StatOrderVO {
    private String day;
    private Long orders;
    private Long customers;
    private BigDecimal amount;
    private BigDecimal pcr;

}
