package com.newland.mall.model.vo.wx;

import com.newland.mall.entity.OrderGoods;
import com.newland.mall.express.dao.ExpressInfo;
import lombok.Data;

import java.util.List;

/**
 * 订单详情vo
 * Author: leell
 * Date: 2023/4/5 15:15:08
 */
@Data
public class OrderDetailVO {
    private OrderInfoVO orderInfo;
    private List<OrderGoods> orderGoods;
    private ExpressInfo expressInfo;
}
