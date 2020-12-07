package com.geniu.concurrent.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther: zhongshibo
 * @Date: 2020/12/7 14:44
 */
public class TestCountDownLatch2 {

    public static AtomicLong num = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        //倒计时计数器，
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    num.getAndIncrement();
                }
                //计数器减1
                latch.countDown();
            }).start();
        }
        //打开开关
        latch.await();
        System.out.println(num.get());
    }

}
