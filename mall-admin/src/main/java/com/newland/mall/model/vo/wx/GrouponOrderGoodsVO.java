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
public class GrouponOrderGoodsVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long goodsId;
    private String goodsName;
    private Integer number;
    private BigDecimal retailPrice;
    private String picUrl;
    private String goodsSpecificationValues;
}
