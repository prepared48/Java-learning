package com.geniu.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 限流器
 * 限流器流速：2个请求/秒
 *
 * @Author: zhongshibo
 * @Date: 2021/1/8 14:18
 */
public class TestRateLimiter2 {
    final static RateLimiter rateLimiter = RateLimiter.create(15); // rate is "2 permits per second"

    public static void main(String[] args) {
        List<Runnable> tasks = new ArrayList<>();
        for(int i = 0; i < 10000; i++) {
            int finalI = i;
            tasks.add(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI + ": " + new Random().nextLong());
                }
            });
        }

        submitTasks(tasks, Executors.newFixedThreadPool(4));
    }

    public static void submitTasks(List<Runnable> tasks, Executor executor) {
        for (Runnable task : tasks) {
            rateLimiter.acquire(); // may wait
            executor.execute(task);
        }
    }
}
