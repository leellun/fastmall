package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * 用户状态 0 可用, 1 禁用, 2 注销
 * Author: leell
 * Date: 2023/4/3 00:04:12
 */
public enum UserStatusEnum implements IBasicEnum {
    AVAILABLE(0, "可用"),
    FORBIDDEN(1, "禁用"),
    UNSUBSCRIBE(2, "注销"),
    ;
    private Integer key;
    private String desc;

    UserStatusEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public UserStatusEnum valueOf(Integer key) {
        for (UserStatusEnum item : values()) {
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
