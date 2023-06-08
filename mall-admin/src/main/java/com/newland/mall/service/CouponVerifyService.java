package com.newland.mall.service;

import com.newland.mall.entity.Cart;
import com.newland.mall.entity.Coupon;

import java.math.BigDecimal;
import java.util.List;

/**
 * 检查优惠卷是否合适
 * Author: leell
 * Date: 2023/4/3 23:21:32
 */
public interface CouponVerifyService {
    /**
     * 检测优惠券是否适合
     * @param userId
     * @param couponId
     * @param userCouponId
     * @param checkedGoodsPrice
     * @param cartList
     * @return
     */
    Coupon checkCoupon(Long userId, Long couponId, Long userCouponId, BigDecimal checkedGoodsPrice, List<Cart> cartList);
}
