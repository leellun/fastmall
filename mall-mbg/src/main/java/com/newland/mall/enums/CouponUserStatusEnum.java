package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * 使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架；
 * Author: leell
 * Date: 2023/3/19 20:40:44
 */
public enum CouponUserStatusEnum implements IBasicEnum {
    STATUS_USABLE(0, "未使用"),
    STATUS_USED(1, "已使用"),
    STATUS_EXPIRED(2, "已过期"),
    STATUS_OUT(3, "已经下架"),
    ;
    private Integer key;
    private String desc;

    CouponUserStatusEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public Integer getKey() {
        return key;
    }
    public static CouponUserStatusEnum valueOf(Integer key) {
        for (CouponUserStatusEnum t : values()) {
            if (t.getKey().equals(key)) {
                return t;
            }
        }
        return null;
    }
}
