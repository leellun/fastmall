package com.newland.cache.key;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 自定义的缓存key生成器
 *
 * @author liulun
 * @date 2023/2/28 11:02
 */
@Slf4j
@Component("cacheKeyGenerator")
public class CacheKeyGenerator implements KeyGenerator {

    private final static String SEPARATE = ":";

    @Override
    public Object generate(Object target, Method method, Object... params) {
        String className = target.getClass().getName();
        String methodName = method.getName();
        String paramNames = "";
        if (null != params) {
            paramNames = JSON.toJSONString(params);
        }
        String key = generate(className, methodName, paramNames);
        log.info("generate -> " + key);
        return key;
    }

    public static String generate(String clsName, String methodName, String params) {
        StringBuilder sb = new StringBuilder();
        sb.append(clsName);
        sb.append(SEPARATE);
        sb.append(methodName);
        if (!StringUtils.hasText(params)) {
            sb.append(SEPARATE);
            sb.append(params);
        }
        return sb.toString();
    }
}