package com.geniu.concurrent.ThreadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程池测试
 * 使用默认拒绝策略，直接报错
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        LinkedBlockingQueue<Runnable> queue =
                new LinkedBlockingQueue<Runnable>(5);
        // 创建线程池 5 10 60s
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60,
                TimeUnit.SECONDS, queue);

        for (int i = 1; i <= 16; i++) {
            threadPool.execute(
                    new Thread(new ThreadTest(), "Thread".concat(i + "")));

            System.out.println("线程池中活跃的线程数： " + threadPool.getPoolSize());

            if (queue.size() > 0) {
                System.out.println("----------------队列中阻塞的线程数" + queue.size());
            }
        }
        threadPool.shutdown();
    }

}
