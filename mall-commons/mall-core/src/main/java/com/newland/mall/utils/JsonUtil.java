package com.newland.mall.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * json转换工具
 */
public class JsonUtil {
    public static String toJSONString(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T parseObject(String str, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(str, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> parseList(String str, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
            return mapper.readValue(str, javaType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
