package com.newland.mall.enums;

import com.newland.mall.enumeration.IBasicEnum;

/**
 * 团购规则状态，正常上线则0，到期自动下线则1，管理手动下线则2
 * Author: leell
 * Date: 2023/3/19 11:47:48
 */
public enum GrouponRulesStatusEnum implements IBasicEnum {
    RULE_STATUS_ON(0,"正常上线"),
    RULE_STATUS_DOWN_EXPIRE(1,"到期自动下线"),
    RULE_STATUS_DOWN_ADMIN(2,"管理手动下线"),
            ;
    private Integer key;
    private String desc;
    GrouponRulesStatusEnum(Integer key,String desc){
        this.key=key;
        this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getKey() {
        return key;
    }
}
