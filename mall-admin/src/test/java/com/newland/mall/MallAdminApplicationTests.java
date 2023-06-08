package com.newland.mall;

import com.newland.mall.enumeration.IBasicEnum;
import com.newland.mall.enums.OrderShowTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallAdminApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        Class<? extends Enum> clazz = OrderShowTypeEnum.class;
        IBasicEnum[] iBasicEnums = (IBasicEnum[]) clazz.getEnumConstants();
        System.out.println(iBasicEnums[0].getKey());
    }
}
