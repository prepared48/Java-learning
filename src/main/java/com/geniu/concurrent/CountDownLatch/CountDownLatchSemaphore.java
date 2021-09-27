package com.geniu.concurrent.CountDownLatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 欧科云链 面试题
 *
 */
@Slf4j
public class CountDownLatchSemaphore {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    /**
     * 如果是StringBuffer，结果固定是5000；如果是 StringBuilder，结果小于5000
     */
    public static StringBuilder stringBuilder = new StringBuilder();

    private static void update() {
        stringBuilder.append("1");
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for(int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            countDownLatch.countDown();
        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("size: {}", stringBuilder.length());
    }

}
