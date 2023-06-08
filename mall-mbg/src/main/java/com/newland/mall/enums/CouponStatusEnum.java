package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * 优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。
 * Author: leell
 * Date: 2023/3/18 16:35:44
 */
public enum CouponStatusEnum implements IBasicEnum {
    NORMAL (0,"正常可用"),
    EXPIRE(1,"过期"),
    SOLD_OUT(2,"下架"),
            ;
    private Integer key;
    private String desc;
    CouponStatusEnum(Integer key, String desc){
        this.key=key;
        this.desc=desc;
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
