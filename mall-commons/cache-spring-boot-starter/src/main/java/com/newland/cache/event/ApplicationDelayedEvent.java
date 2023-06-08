package com.newland.cache.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.Duration;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟任务事件
 *
 * @author liulun
 * @date 2023/2/28 12:46
 */
@EqualsAndHashCode(callSuper = false)
public class ApplicationDelayedEvent extends ApplicationEvent implements Delayed {

    private static final long serialVersionUID = 1L;

    public ApplicationDelayedEvent(Object source) {
        this(source, 0L);
    }

    public ApplicationDelayedEvent(Object source, long delaySeconds) {
        super(source, Clock.offset(Clock.systemDefaultZone(), Duration.ofSeconds(delaySeconds)));
    }

    @Override
    public int compareTo(Delayed o) {
        // 最好用NANOSECONDS，更精确，但是用处不大
        long delta = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        return (int) delta;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        // 最好用NANOSECONDS，更精确，但是用处不大，负数也会认为到时间了
        long millis = this.getTimestamp();
        long currentTimeMillis = System.currentTimeMillis();
        long sourceDuration = millis - currentTimeMillis;
        return unit.convert(sourceDuration, unit);
    }
}