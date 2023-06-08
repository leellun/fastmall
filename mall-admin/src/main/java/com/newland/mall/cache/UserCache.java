package com.newland.mall.cache;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 用户相关信息缓存
 *
 * @author liulun
 * @date 2023/2/28 10:29
 */
@Component
public class UserCache {
    @Autowired
    private Producer kaptchaProducer;

    @CachePut(value = "cacheKaptcha", key = "#id")
    public String getPutKaptcha(Long id) {
        return kaptchaProducer.createText();
    }

    @Cacheable(value = "cacheKaptcha", key = "#id")
    public String getKaptcha(Long id) {
        return null;
    }

}
