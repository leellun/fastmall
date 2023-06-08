package com.newland.mall.mapper;

import com.newland.mall.entity.Coupon;
import com.newland.mall.entity.CouponUser;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 优惠券用户使用表 Mapper 接口
 *
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface CouponUserMapper extends BaseMapper<CouponUser> {
    /**
     * 用户优惠卷列表
     *
     * @param userId   用户id
     * @param couponId 优惠卷id
     * @param status   状态
     * @return
     */
    List<CouponUser> listCouponUsers(@Param("userId") Long userId, @Param("couponId") Long couponId, @Param("status") Integer status);

    /**
     * 查询指定订单id的优惠卷
     *
     * @param orderId
     * @return
     */
    List<CouponUser> listByOrderId(@Param("orderId") Long orderId);

    /**
     * 获取用户指定优惠卷数量
     *
     * @param userId
     * @param couponId
     * @return
     */
    Long countByUserIdAndCouponId(@Param("userId") Long userId, @Param("couponId") Long couponId);

    /**
     * 查询指定用户下所有优惠卷
     *
     * @param userId
     * @return
     */
    List<CouponUser> listByUserId(@Param("userId") Long userId);

    /**
     * 优惠卷已领数量
     *
     * @param couponId
     * @return
     */
    Long countByCouponId(@Param("couponId") Long couponId);

}