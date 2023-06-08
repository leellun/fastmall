package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * 有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期；
 * Author: leell
 * Date: 2023/3/18 16:35:44
 */
public enum CouponTimeTypeEnum implements IBasicEnum {
    TIME_TYPE_DAYS (0,"基于领取时间的有效天数"),
    TIME_TYPE_TIME(1,"start_time和end_time是优惠券有效期"),
            ;
    private Integer key;
    private String desc;
    CouponTimeTypeEnum(Integer key, String desc){
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
