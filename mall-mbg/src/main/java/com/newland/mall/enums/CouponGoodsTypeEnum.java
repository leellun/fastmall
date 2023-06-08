package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * 商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。
 * Author: leell
 * Date: 2023/3/18 16:35:44
 */
public enum CouponGoodsTypeEnum implements IBasicEnum {
    GOODS_TYPE_ALL (0,"全商品"),
    GOODS_TYPE_CATEGORY(1,"类目限制"),
    GOODS_TYPE_GOODS(2,"商品限制"),
            ;
    private Integer key;
    private String desc;
    CouponGoodsTypeEnum(Integer key, String desc){
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
