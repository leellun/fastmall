package com.newland.cache.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author liulun
 * @date 2023/2/28 12:57
 */
@Slf4j
@Configuration
@EnableAsync
@ConditionalOnClass(ApplicationDelayEventPublisher.class)
public class DelayEventConfiguration {

    /**
     * 不能和监听线程放到一个线程池，不然无法执行
     *
     * @return
     */
    @Bean("poolTaskExecutor")
    public ThreadPoolTaskExecutor poolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(10000);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("task_");

        // 线程池对拒绝任务的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 初始化
        executor.initialize();
        return executor;
    }

    @Bean("watchTaskExecutor")
    public ThreadPoolTaskExecutor watchTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setQueueCapacity(1);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("watch_task_");

        // 线程池对拒绝任务的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        executor.initialize();
        return executor;
    }
}
