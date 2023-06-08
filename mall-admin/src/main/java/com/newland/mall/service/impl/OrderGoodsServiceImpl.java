package com.newland.mall.service.impl;

import com.newland.mall.entity.OrderGoods;
import com.newland.mall.mapper.OrderGoodsMapper;
import com.newland.mall.service.OrderGoodsService;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单商品表 服务实现类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class OrderGoodsServiceImpl extends ServiceImpl<OrderGoodsMapper, OrderGoods> implements OrderGoodsService {
    @Override
    public List<OrderGoods> listByOrderId(Long orderId) {
        return baseMapper.listByOrderId(orderId);
    }

    @Override
    public void deleteByOrderId(Long orderId) {
        baseMapper.deleteLogicalByOrderId(orderId);
    }

    @Override
    public void addOrderGoods(OrderGoods orderGoods) {
        baseMapper.insert(orderGoods);
    }

    @Override
    public Integer countByOrderId(Long orderId) {
        return baseMapper.countByOrderId(orderId);
    }
}