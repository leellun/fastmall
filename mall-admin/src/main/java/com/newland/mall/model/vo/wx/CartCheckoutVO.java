package com.newland.mall.model.vo.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.entity.Address;
import com.newland.mall.entity.Cart;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车下单
 *
 * @author liulun
 * @date 2023/4/4 10:46
 */
@Data
public class CartCheckoutVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long addressId;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long couponId;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userCouponId;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long cartId;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long grouponRulesId;
    private BigDecimal grouponPrice;
    private BigDecimal goodsTotalPrice;
    private BigDecimal freightPrice;
    private BigDecimal couponPrice;
    private BigDecimal orderTotalPrice;
    private BigDecimal actualPrice;
    private Address checkedAddress;
    private Integer availableCouponLength;
    private List<Cart> checkedGoodsList;
}
