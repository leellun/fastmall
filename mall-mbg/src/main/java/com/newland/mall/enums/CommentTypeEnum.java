package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * 评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；
 * @author liulun
 * @date 2023/4/4 14:02
 */
public enum CommentTypeEnum implements IBasicEnum {

    GOODS(0, "则是商品ID"),

    TOPIC(1, "则是专题ID"),
            ;

    private Integer key;
    private String desc;

    CommentTypeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static CommentTypeEnum valueOf(Integer key) {
        for (CommentTypeEnum item : values()) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public IBasicEnum valueOfKey(Integer key) {
        return CommentTypeEnum.valueOf(key);
    }

    public String getDesc() {
        return desc;
    }

    public Integer getKey() {
        return key;
    }
}
