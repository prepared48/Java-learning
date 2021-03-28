package com.geniu.concurrent.deepunderstandingjvm.chapter13;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 另一种解决例子1201问题的方法，效率更好
 * 使用AtomicInteger，使用CAS
 * 可能会有ABA问题，可以使用传统的互斥同步来解决；也可以使用带有标记的原子引用类（AtomicStampedReference）（比较鸡肋）
 *
 * @Author: zhongshibo
 * @Date: 2021/3/28 21:49
 */
public class Test1304AtomicIntegerTest {


    public static AtomicInteger race = new AtomicInteger(0);

    final static CyclicBarrier barrier = new CyclicBarrier(4);

    public static void increase() {
        race.incrementAndGet();
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
