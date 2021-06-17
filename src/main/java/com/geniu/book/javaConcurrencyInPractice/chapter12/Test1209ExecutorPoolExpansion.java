package com.geniu.book.javaConcurrencyInPractice.chapter12;

import junit.framework.TestCase;

/**
 * 验证线程池扩展能力的测试方法
 *
 * @Author: zhongshibo
 * @Date: 2021/3/2 22:30
 */
public class Test1209ExecutorPoolExpansion extends TestCase {

    Test1208TestingThreadFactory threadFactory = new Test1208TestingThreadFactory();

    public void testPoolExpansion() throws InterruptedException {
        int MAX_SIZE = 10;
        for (int i = 0; i < 10 * MAX_SIZE; i++) {
            threadFactory.newThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (InterruptedException e) {
                        // 恢复中断
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        for (int i = 0; i < 20 && threadFactory.numCreated.get() < MAX_SIZE; i++) {
            Thread.sleep(100);
        }

        assertEquals(threadFactory.numCreated.get(), MAX_SIZE);
    }

}
