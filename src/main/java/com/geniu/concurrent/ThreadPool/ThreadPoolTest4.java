package com.geniu.concurrent.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * COLA框架自带的初始化线程池参数
 * 为什么这么设置，参考readme.md
 */
public class ThreadPoolTest4 {

    public static void main(String[] args) {
        getThreadPool();
    }

    public static ExecutorService getThreadPool() {
        ExecutorService defaultExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1,
                Runtime.getRuntime().availableProcessors() + 1,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(1000));
        System.out.println(Runtime.getRuntime().availableProcessors());
        return defaultExecutor;
    }
}
