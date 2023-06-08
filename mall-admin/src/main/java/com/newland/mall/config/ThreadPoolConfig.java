package com.newland.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author: leell
 * Date: 2023/4/5 20:20:11
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(
                10,
                20,
                300,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10000),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
