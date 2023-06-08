package com.newland.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 缓存过期
 * @author liulun
 * @date 2023/2/28 11:00
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheExpire {

    /**
     * 失效时间，默认是60
     * @return
     */
    public long ttl() default 60L;

    /**
     * 单位，默认是秒
     * @return
     */
    public TimeUnit unit() default TimeUnit.SECONDS;
}
