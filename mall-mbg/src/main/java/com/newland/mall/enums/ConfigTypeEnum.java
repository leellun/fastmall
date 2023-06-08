package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * 配置类型 1 商城 2 运费 3 订单 4 小程序
 * Date: 2023/3/18 15:54:01
 * @author leell
 */
public enum ConfigTypeEnum implements IBasicEnum {
    MALL(1, "商城"),
    EXPRESS(2, "运费"),
    ORDER(3, "订单"),
    WX(4, "小程序"),
    ;
    private Integer key;
    private String desc;

    ConfigTypeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getKey() {
        return key;
    }
}
