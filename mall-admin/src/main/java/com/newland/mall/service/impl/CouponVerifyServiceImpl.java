package com.newland.mall.service.impl;

import com.newland.mall.entity.Cart;
import com.newland.mall.entity.Coupon;
import com.newland.mall.entity.CouponUser;
import com.newland.mall.enumeration.BasicEnum;
import com.newland.mall.enums.CouponGoodsTypeEnum;
import com.newland.mall.enums.CouponStatusEnum;
import com.newland.mall.enums.CouponTimeTypeEnum;
import com.newland.mall.mapper.CouponMapper;
import com.newland.mall.mapper.CouponUserMapper;
import com.newland.mall.mapper.GoodsMapper;
import com.newland.mall.service.CouponVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 优惠卷验证
 * Author: leell
 * Date: 2023/4/3 23:21:55
 */
@Service
public class CouponVerifyServiceImpl implements CouponVerifyService {
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private CouponUserMapper couponUserMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 检测优惠券是否适合
     *
     * @param userId
     * @param couponId
     * @param checkedGoodsPrice
     * @return
     */
    public Coupon checkCoupon(Long userId, Long couponId, Long userCouponId, BigDecimal checkedGoodsPrice, List<Cart> cartList) {
        Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
        if (coupon == null || coupon.getDeleted().equals(BasicEnum.YES.getKey())) {
            return null;
        }

        CouponUser couponUser = couponUserMapper.selectByPrimaryKey(userCouponId);
        if (couponUser == null) {
            List<CouponUser> couponUsers = couponUserMapper.listCouponUsers(userId, couponId, CouponStatusEnum.NORMAL.getKey());
            couponUser = couponUsers.size() > 0 ? couponUsers.get(0) : null;
        } else if (!couponId.equals(couponUser.getCouponId())) {
            return null;
        }

        if (couponUser == null) {
            return null;
        }

        // 检查是否超期
        Integer timeType = coupon.getTimeType();
        Integer days = coupon.getDays();
        LocalDateTime now = LocalDateTime.now();
        if (timeType.equals(CouponTimeTypeEnum.TIME_TYPE_TIME.getKey())) {
            if (now.isBefore(coupon.getStartTime()) || now.isAfter(coupon.getEndTime())) {
                return null;
            }
        } else if (timeType.equals(CouponTimeTypeEnum.TIME_TYPE_DAYS.getKey())) {
            LocalDateTime expired = couponUser.getCreateTime().plusDays(days);
            if (now.isAfter(expired)) {
                return null;
            }
        } else {
            return null;
        }

        // 检测商品是否符合
        Map<Long, List<Cart>> cartMap = new HashMap<>();
        //可使用优惠券的商品或分类
        List<Long> goodsValueList = new ArrayList<>(Arrays.asList(coupon.getGoodsValue()));
        Integer goodType = coupon.getGoodsType();

        if (goodType.equals(CouponGoodsTypeEnum.GOODS_TYPE_CATEGORY.getKey()) ||
                goodType.equals((CouponGoodsTypeEnum.GOODS_TYPE_GOODS.getKey()))) {
            for (Cart cart : cartList) {
                Long key = goodType.equals(CouponGoodsTypeEnum.GOODS_TYPE_GOODS.getKey()) ? cart.getGoodsId() :
                        goodsMapper.selectByPrimaryKey(cart.getGoodsId()).getCategoryId();
                List<Cart> carts = cartMap.get(key);
                if (carts == null) {
                    carts = new LinkedList<>();
                }
                carts.add(cart);
                cartMap.put(key, carts);
            }
            //购物车中可以使用优惠券的商品或分类
            goodsValueList.retainAll(cartMap.keySet());
            //可使用优惠券的商品的总价格
            BigDecimal total = new BigDecimal(0);

            for (Long goodsId : goodsValueList) {
                List<Cart> carts = cartMap.get(goodsId);
                for (Cart cart : carts) {
                    total = total.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
                }
            }
            //是否达到优惠券满减金额
            if (total.compareTo(coupon.getMin()) == -1) {
                return null;
            }
        }

        // 检测订单状态
        Integer status = coupon.getStatus();
        if (!status.equals(CouponStatusEnum.NORMAL.getKey())) {
            return null;
        }
        // 检测是否满足最低消费
        if (checkedGoodsPrice.compareTo(coupon.getMin()) == -1) {
            return null;
        }

        return coupon;
    }
}
