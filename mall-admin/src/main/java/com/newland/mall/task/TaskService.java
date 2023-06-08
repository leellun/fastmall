package com.newland.mall.task;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class TaskService {
    private DelayQueue<Task> delayQueue =  new DelayQueue<Task>();

    @PostConstruct
    private void init() {
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10));
        threadPoolExecutor.execute(() -> {
            while (true) {
                try {
                    Task task = delayQueue.take();
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addTask(Task task){
        if(delayQueue.contains(task)){
            return;
        }
        delayQueue.add(task);
    }

    public void removeTask(Task task){
        delayQueue.remove(task);
    }

}
