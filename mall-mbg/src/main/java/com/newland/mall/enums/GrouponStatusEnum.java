package com.newland.mall.enums;

import cn.hutool.core.getter.GroupedTypeGetter;
import com.newland.mall.enumeration.IBasicEnum;

/**
 * 团购活动状态，开团未支付则0，开团中则1，开团成功则2，开团失败则3
 * Author: leell
 * Date: 2023/3/19 10:48:50
 */
public enum GrouponStatusEnum implements IBasicEnum {
    STATUS_NONE(0, "开团未支付"),
    STATUS_ON(1, "开团中"),
    STATUS_SUCCEED(2, "开团成功"),
    STATUS_FAIL(3, "开团失败"),
    ;

    private Integer key;
    private String desc;

    GrouponStatusEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static GrouponStatusEnum valueOf(Integer key) {
        for (GrouponStatusEnum t : values()) {
            if (t.getKey().equals(key)) {
                return t;
            }
        }
        return null;
    }
}
