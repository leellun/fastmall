package com.newland.mall.mapper;

import com.newland.mall.entity.Cart;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购物车商品表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface CartMapper extends BaseMapper<Cart> {
    /**
     * 通过货品id 更新购物车
     * @param cart
     */
    void updateByProductId(Cart cart);

    /**
     * 获取用户下购物车
     * @param userId
     * @return
     */
    List<Cart> listByUserId(@Param("userId") Long userId);

    /**
     * 获取购物车
     * @param goodsId 商品基本信息id
     * @param productId 货物id
     * @param userId 用户id
     * @return
     */
    Cart getByGoodsIdAndProductIdAndUserId(@Param("goodsId")Long goodsId,@Param("productId") Long productId,@Param("userId") Long userId);

    /**
     * 删除
     * @param userId 用户id
     * @param productIds 货物id列表
     */
    Integer deleteByUserIdAndProductIds(@Param("userId") Long userId,@Param("productIds") List<Long> productIds);
    /**
     * 删除
     * @param productIds 货物id列表
     */
    Integer deleteByProductIds(@Param("productIds") List<Long> productIds);
    /**
     * 更新选中状态
     * @param userId 用户id
     * @param productIds 货物id列表
     * @param checked 选中状态
     */
    void updateCheck(@Param("userId") Long userId,@Param("productIds") List<Long> productIds,@Param("checked") Integer checked);

    /**
     * 删除用户下购物车
     * @param userId
     */
    void deleteByUserId(@Param("userId") Long userId);
}