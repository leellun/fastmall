package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * 订单显示
 * Author: leell
 * Date: 2023/4/5 13:43:37
 */
public enum OrderShowTypeEnum implements IBasicEnum {
    ALL(0, "所有订单"),
    PENDING_PAYMENT(1, "待付款订单"),
    PENDING_DELIVER(2, "待发货订单"),
    PENDING_TAKE(3, "待收货订单"),
    PENDING_COMMENT(4, "待评价订单"),
    ;
    private Integer key;
    private String desc;

    OrderShowTypeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static OrderShowTypeEnum valueOf(Integer key) {
        for (OrderShowTypeEnum item : values()) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public Integer getKey() {
        return key;
    }
}
