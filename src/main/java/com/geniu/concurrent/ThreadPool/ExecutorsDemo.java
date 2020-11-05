package com.geniu.concurrent.ThreadPool;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 测试使用 guava 提供的 ThreadFactoryBuilder 创建线程池
 *
 * @author shibo_zhong
 * @date 2020/11/03
 */
public class ExecutorsDemo {

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

    private static ExecutorService pool = new ThreadPoolExecutor(5, 20, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(30), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            pool.execute(new SubThread());
        }
    }
}