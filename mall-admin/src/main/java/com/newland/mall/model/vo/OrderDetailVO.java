package com.newland.mall.model.vo;

import com.newland.mall.entity.Order;
import com.newland.mall.entity.OrderGoods;
import lombok.Data;

import java.util.List;

/**
 * 订单详情
 * Author: leell
 * Date: 2023/3/19 19:13:01
 * @author leell
 */
@Data
public class OrderDetailVO {
    private Order order;
    private List<OrderGoods> orderGoods;
    private UserVO user;
}
