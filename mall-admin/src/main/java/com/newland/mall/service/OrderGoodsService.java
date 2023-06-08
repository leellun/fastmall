package com.newland.mall.service;

import com.newland.mall.entity.OrderGoods;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 订单商品表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface OrderGoodsService extends IService<OrderGoods> {
    /**
     * 根据订单id获取商品
     * @param orderId
     * @return
     */
    List<OrderGoods> listByOrderId(Long orderId);

    /**
     * 删除订单商品
     * @param orderId
     */
    void deleteByOrderId(Long orderId);

    /**
     * 添加商品订单
     * @param orderGoods
     */
    void addOrderGoods(OrderGoods orderGoods);

    /**
     * 通过订单id统计
     * @param orderId
     * @return
     */
    Integer countByOrderId(Long orderId);
}