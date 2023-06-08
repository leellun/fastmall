package com.newland.mall.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * 订单相关操作
 * Author: leell
 * Date: 2023/4/2 22:15:26
 */
public class OrderUtils {
    public static String generateAftersaleSn(CountCallback callback) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = df.format(LocalDate.now());
        String sn = now + getRandomNum(6);
        while (callback.get(sn) != 0) {
            sn = now + getRandomNum(6);
        }
        return sn;
    }

    private static String getRandomNum(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public interface CountCallback {
        Long get(String sn);
    }
}
