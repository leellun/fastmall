package com.newland.mall.model.vo.wx;

import com.newland.mall.entity.Aftersale;
import com.newland.mall.entity.Order;
import com.newland.mall.entity.OrderGoods;
import lombok.Data;

import java.util.List;

/**
 * Author: leell
 * Date: 2023/4/2 20:34:00
 */
@Data
public class AftersaleVO {
    private Aftersale aftersale;
    private List<OrderGoods> orderGoods;
    private Order order;
}
