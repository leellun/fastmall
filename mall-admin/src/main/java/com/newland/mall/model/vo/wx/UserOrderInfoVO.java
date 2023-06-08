package com.newland.mall.model.vo.wx;

import lombok.Data;

/**
 * 用户订单信息
 * Author: leell
 * Date: 2023/4/6 20:19:46
 */
@Data
public class UserOrderInfoVO {
    /**
     * 未支付订单
     */
    int unpaid;
    /**
     * 未发货订单
     */
    int unship;
    /**
     * 未收获订单
     */
    int unrecv;
    /**
     * 未评价订单
     */
    int uncomment;
}
