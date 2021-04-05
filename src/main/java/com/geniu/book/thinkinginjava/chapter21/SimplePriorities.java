package com.geniu.book.thinkinginjava.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程优先级
 *
 * @Author: zhongshibo
 * @Date: 2021/4/5 22:27
 */
public class SimplePriorities implements Runnable {

    private int countDown = 5;

    private volatile double d;

    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    @Override
    public void run() {
        // 设置优先级的处理
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 0; i < 100000; i++) {
                d += (Math.PI + Math.E) / (i);
                if (i % 1000 == 0) {
                    // 让出CPU时间，【建议】其他具有相同优先级的线程可以执行。
                    // 如果没有这一步，高优先级的线程，明显执行时间靠后
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}
