package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
 * Author: leell
 * Date: 2023/4/2 21:52:58
 */
public enum AftersaleStatusEnum implements IBasicEnum {
    STATUS_INIT(0, "可申请"),
    STATUS_REQUEST(1, "用户已申请"),
    STATUS_RECEPT(2, "管理员审核通过"),
    STATUS_REFUND(3, "管理员退款成功"),
    STATUS_CANCEL(4, "管理员审核拒绝"),
    STATUS_REJECT(5, "用户已取消"),
    ;
    private Integer key;
    private String desc;

    AftersaleStatusEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public AftersaleStatusEnum valueOf(Integer key) {
        for (AftersaleStatusEnum item : values()) {
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
