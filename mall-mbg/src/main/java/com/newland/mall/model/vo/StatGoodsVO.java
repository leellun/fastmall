package com.newland.mall.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Author: leell
 * Date: 2023/3/19 23:49:55
 */
@Data
public class StatGoodsVO {
    private String day;
    private Long orders;
    private Long products;
    private BigDecimal amount;
}
