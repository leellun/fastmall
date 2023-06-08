package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.CouponUser;
import com.newland.mall.model.vo.wx.CouponVO;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 优惠券用户使用表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface CouponUserService extends IService<CouponUser> {
    /**
     * 获取用户优惠卷
     * @param userId 用户id
     * @param couponId 优惠卷id
     * @param status 状态
     * @param pageEntity 分页
     * @return
     */
    PageInfo<CouponUser> listCouponUser(Long userId, Long couponId, Integer status, PageEntity pageEntity);
    /**
     * 用户优惠卷列表
     * @param userId
     * @param status
     * @param pageEntity
     * @return
     */
    PageInfo<CouponVO> listUserCoupon(Long userId, Integer status, PageEntity pageEntity);
    /**
     * 获取用户优惠卷
     * @param orderId
     * @return
     */
    List<CouponUser> listByOrderId(Long orderId);

    /**
     * 分发注册优惠券
     * @param userId
     */
    void assignForRegister(Long userId);
    /**
     * 获取用户优惠卷
     * @param userId
     * @return
     */
    List<CouponUser> listByUserId(Long userId);

    /**
     * 当前购物车可用优惠卷
     * @param userId
     * @param cartId
     * @param grouponRulesId
     */
    List<CouponVO> selectList(Long userId, Long cartId, Long grouponRulesId);

    /**
     * 优惠卷领取
     * @param userId
     * @param couponId
     */
    void receive(Long userId, Long couponId);

    /**
     * 兑换优惠卷
     * @param userId 用户id
     * @param code 兑换码
     */
    void exchange(Long userId, String code);
}