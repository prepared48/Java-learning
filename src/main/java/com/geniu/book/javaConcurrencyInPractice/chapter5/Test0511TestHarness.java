package com.geniu.book.javaConcurrencyInPractice.chapter5;

import java.util.concurrent.CountDownLatch;

/**
 * 在计时测试中使用 CountDownLatch  来启动和停止线程
 * CountDownLatch：闭锁
 *
 * @Author: zhongshibo
 * @Date: 2021/2/21 22:54
 */
public class Test0511TestHarness {

    /**
     * 统计 nThreads 个线程并发执行所需要的时间
     * 所以：需要等待 nThreads 个线程都执行完，然后在获取结束时间
     *
     * @param nThreads
     * @param task
     * @return
     * @throws InterruptedException
     */
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
}