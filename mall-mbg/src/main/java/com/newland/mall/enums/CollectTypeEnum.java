package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * 收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID
 * Date: 2023/3/18 15:54:01
 *
 * @author leell
 */
public enum CollectTypeEnum implements IBasicEnum {

    GOODS(0, "则是商品ID"),

    TOPIC(1, "则是专题ID"),
    ;

    private Integer key;
    private String desc;

    CollectTypeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static CollectTypeEnum valueOf(Integer key) {
        for (CollectTypeEnum item : values()) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public IBasicEnum valueOfKey(Integer key) {
        return CollectTypeEnum.valueOf(key);
    }

    public String getDesc() {
        return desc;
    }

    public Integer getKey() {
        return key;
    }
}
