package com.newland.mall.service;

import com.newland.mall.entity.Cart;
import com.newland.mall.model.dto.wx.CartDTO;
import com.newland.mall.model.vo.wx.CartCheckoutVO;
import com.newland.mybatis.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车商品表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface CartService extends IService<Cart> {
    /**
     * 更新购物车
     * @param productId 商品货品id
     * @param goodsSn 商品编号
     * @param goodsName 商品名称
     * @param price 价格
     * @param url 地址
     */
    void updateProduct(Long productId, String goodsSn, String goodsName, BigDecimal price, String url);

    /**
     * 获取用户下购物车
     * @param userId
     * @return
     */
    List<Cart> listUserCarts(Long userId);

    /**
     * 添加购物车
     * @param userId
     * @param cartDTO
     */
    void addCart(Long userId, CartDTO cartDTO);

    /**
     * 立即购买
     * @param userId
     * @param cartDTO
     */
    Long fastAddCart(Long userId, CartDTO cartDTO);

    /**
     * 更新购物车
     * @param userId
     * @param cartDTO
     */
    void updateCart(Long userId, CartDTO cartDTO);

    /**
     * 删除购物车
     * @param userId
     * @param productIds
     */
    void delete(Long userId, List<Integer> productIds);

    /**
     * 购物车下单
     * @param userId 用户id
     * @param cartId 购物车id
     * @param addressId 地址id
     * @param couponId 优惠卷id
     * @param userCouponId 用户优惠卷id
     * @param grouponRulesId 团购规则id
     */
    CartCheckoutVO checkout(Long userId, Long cartId, Long addressId, Long couponId, Long userCouponId, Long grouponRulesId);

    /**
     * 更新选中状态
     * @param userId
     * @param productIds
     * @param isChecked
     */
    void updateCheck(Long userId, List<Long> productIds, Integer isChecked);

    /**
     * 删除购物车
     * @param cartId
     */
    void deleteById(Long cartId);

    /**
     * 清空购物车
     * @param userId
     */
    void clearGoods(Long userId);
}