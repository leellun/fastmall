package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * 性别
 * @author liulun
 * @date 2023/4/3 13:12
 */
public enum UserGenderEnum implements IBasicEnum {
    UN_KNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女"),
            ;
    private Integer key;
    private String desc;

    UserGenderEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static UserGenderEnum valueOf(Integer key) {
        for (UserGenderEnum item : values()) {
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
