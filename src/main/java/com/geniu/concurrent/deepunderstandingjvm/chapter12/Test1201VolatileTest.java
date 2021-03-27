package com.geniu.concurrent.deepunderstandingjvm.chapter12;

import java.util.concurrent.CyclicBarrier;

/**
 * volatile不能保证线程安全
 * volatile 适合在1)使用毒丸对象停止线程的场景;
 * 2)适合场景：DCL单例，禁止指令重排序
 * <p>
 * 问题：为啥默认有两个线程？
 *
 * @Author: zhongshibo
 * @Date: 2021/3/27 19:07
 */
public class Test1201VolatileTest {

    public static volatile int race = 0;

    final static CyclicBarrier barrier = new CyclicBarrier(4);

    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 4;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
//            System.out.println(Thread.currentThread().getName() + "第" + i + "次执行增加操作");
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        System.out.println(Thread.activeCount());
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.activeCount());
        System.out.println(race);
    }
}
