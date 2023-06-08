package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * 用户等级 0 普通用户，1 VIP用户，2 高级VIP用户
 * Author: leell
 * Date: 2023/4/3 00:04:12
 */
public enum UserLevelEnum implements IBasicEnum {
    GENERAL_USER(0, "普通用户"),
    VIP_USER(1, "VIP用户"),
    ADVANCED_VIP_USER(2, "高级VIP用户"),
    ;
    private Integer key;
    private String desc;

    UserLevelEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public UserLevelEnum valueOf(Integer key) {
        for (UserLevelEnum item : values()) {
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
