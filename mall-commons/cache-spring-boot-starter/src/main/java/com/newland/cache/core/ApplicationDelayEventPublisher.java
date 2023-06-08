package com.newland.cache.core;

import com.newland.cache.event.ApplicationDelayedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;

/**
 *
 * @author liulun
 * @date 2023/2/28 12:48
 */

@Slf4j
@Component
public class ApplicationDelayEventPublisher implements ApplicationRunner {

    /**
     * ApplicationDelayedEvent需要import进来
     */
    private DelayQueue<ApplicationDelayedEvent> delayQueue = new DelayQueue<>();

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    @Qualifier("watchTaskExecutor")
    private ThreadPoolTaskExecutor watchTaskExecutor;
    @Async("poolTaskExecutor")
    @EventListener
    public void publishEvent(ApplicationDelayedEvent event) {
        boolean result = delayQueue.offer(event);
        log.info("加入延迟队列。。。。{}", result);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        watchTaskExecutor.execute(() -> watchThread());
    }

    private void watchThread() {
        while (true) {
            try {
                log.info("启动延时任务的监听线程。。。。");
                ApplicationDelayedEvent event = this.delayQueue.take();
                log.info("接收到延时任务执行。。。{}", ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
                eventPublisher.publishEvent(event);
            } catch (InterruptedException e) {
                log.info("启动延时任务的监听线程关闭");
                this.delayQueue.clear();
                break;
            }
        }
    }
}
