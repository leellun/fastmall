package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Coupon;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 优惠券信息及规则表 服务类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface CouponService extends IService<Coupon> {
    /**
     * 获取优惠卷列表
     *
     * @param name       名称
     * @param type       类型
     * @param status     状态
     * @param pageEntity 分页
     * @return
     */
    PageInfo<Coupon> listCoupon(String name, Integer type, Integer status, PageEntity pageEntity);

    /**
     * 分页查询优惠卷
     *
     * @param pageEntity
     * @return
     */
    PageInfo<Coupon> listCoupon(PageEntity pageEntity);

    /**
     * 添加优惠卷
     *
     * @param coupon
     */
    void add(Coupon coupon);

    /**
     * 优惠卷详情
     *
     * @param id
     * @return
     */
    Coupon get(Long id);

    /**
     * 更新优惠卷
     *
     * @param coupon
     */
    void update(Coupon coupon);

    /**
     * 删除优惠卷
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 有效优惠卷
     *
     * @param userId
     * @param pageSize
     * @return
     */
    List<Coupon> listAvailableCoupons(Long userId, int pageSize);

    /**
     * 优惠卷
     *
     * @param pageSize
     * @return
     */
    List<Coupon> listCoupons(int pageSize);
}