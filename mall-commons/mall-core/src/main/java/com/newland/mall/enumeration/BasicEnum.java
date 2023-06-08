package com.newland.mall.enumeration;


import com.newland.mall.constant.Constant;

/**
 * 基础枚举常量 1：true 0：false
 * Author: leell
 * Date: 2022/12/7 00:03:46
 */
public enum BasicEnum implements IBasicEnum {
    YES(Constant.FLAG_YES, "启用/是"),
    NO(Constant.FLAG_NO, "关闭/否"),
    ;

    private Integer key;
    private String desc;

    BasicEnum(Integer code, String desc) {
        this.key = code;
        this.desc = desc;
    }

    public Integer getKey() {
        return this.key;
    }

    public String getDesc() {
        return this.desc;
    }
}
