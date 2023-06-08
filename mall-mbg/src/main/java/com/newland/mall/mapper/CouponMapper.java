package com.newland.mall.mapper;

import com.newland.mall.entity.Coupon;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 优惠券信息及规则表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface CouponMapper extends BaseMapper<Coupon> {
    /**
     * 优惠卷列表
     * @param name 名称
     * @param type 类型
     * @param status 状态
     * @return
     */
    List<Coupon> listCoupon(@Param("name") String name,@Param("type") Integer type,@Param("status") Integer status);

    /**
     * 获取count 根据code和key
     * @param code 优惠码
     * @param type 类型
     * @return
     */
    Long countByCodeAndStatus(@Param("code") String code,@Param("type")  Integer type);

    /**
     * 优惠卷列表
     * @param type
     * @param status
     * @return
     */
    List<Coupon> listByTypeAndStatus(@Param("type") Integer type,@Param("status") Integer status);

    /**
     * 根据兑换码查找优惠卷
     * @param code
     * @return
     */
    Coupon getByCode(@Param("code") String code);

    /**
     * 有效优惠卷
     * @param userId 用户id
     * @return
     */
    List<Coupon> listAvailableCoupons(@Param("userId") Long userId);

    /**
     * 所有优惠卷
     * @return
     */
    List<Coupon> listCoupons();
}