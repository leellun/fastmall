package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * 售后类型，0是未收货退款，1是已收货（无需退货）退款，2用户退货退款
 * Author: leell
 * Date: 2023/4/2 21:52:58
 */
public enum AftersaleTypeEnum implements IBasicEnum {
    TYPE_GOODS_MISS(0, "未收货退款"),
    TYPE_GOODS_NEEDLESS(1, "已收货（无需退货）退款"),
    TYPE_GOODS_REQUIRED(2, "用户退货退款"),
    ;
    private Integer key;
    private String desc;

    AftersaleTypeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public AftersaleTypeEnum valueOf(Integer key) {
        for (AftersaleTypeEnum item : values()) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getKey() {
        return key;
    }
}
