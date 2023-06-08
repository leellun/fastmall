package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * Author: leell
 * Date: 2023/3/18 11:15:34
 */
public enum MallErrorEnum implements IBasicEnum {
    /**
     * 可申请状态
     */
    ORDER_REFUND_FAILED(621,"订单退款失败"),
    ;
    private Integer key;
    private String desc;

    MallErrorEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
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
