package com.newland.mall.model.vo.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单商品
 * Author: leell
 * Date: 2023/4/5 09:37:01
 */
@Data
public class OrderGoodsVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String goodsName;
    private Integer number;
    private String picUrl;
    private String specifications;
    private BigDecimal price;
}
