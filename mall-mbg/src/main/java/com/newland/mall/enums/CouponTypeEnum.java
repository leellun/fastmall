package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * 优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换；
 * Author: leell
 * Date: 2023/3/18 16:35:44
 */
public enum CouponTypeEnum implements IBasicEnum {
    GENERAL(0,"通用券"),
    REGISTER(1,"注册赠券"),
    CODE(2,"优惠券码兑换"),
            ;
    private Integer key;
    private String desc;
    CouponTypeEnum(Integer key,String desc){
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
