package com.newland.mall.enumeration;

import java.lang.reflect.Method;

/**
 * @author leell
 */
public interface IBasicEnum {
    public String getDesc();

    public Integer getKey();

    public default IBasicEnum valueOfKey(Integer key) {
        try {
            Method method = getClass().getMethod("values");
            IBasicEnum[] items = (IBasicEnum[]) method.invoke(null);
            for(IBasicEnum item:items){
                if(item.getKey().equals(key)){
                    return item;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
