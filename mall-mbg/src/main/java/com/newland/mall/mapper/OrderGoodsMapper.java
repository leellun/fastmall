package com.newland.mall.mapper;

import com.newland.mall.entity.OrderGoods;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单商品表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface OrderGoodsMapper extends BaseMapper<OrderGoods> {
    /**
     * 根据订单id获取商品
     * @param orderId
     * @return
     */
    List<OrderGoods> listByOrderId(@Param("orderId") Long orderId);

    /**
     * 删除订单关联商品
     * @param orderId
     */
    void deleteLogicalByOrderId(@Param("orderId") Long orderId);

    /**
     * 统计订单id对应count
     * @param orderId
     * @return
     */
    Integer countByOrderId(@Param("orderId") Long orderId);
}