package com.geniu.concurrent.volatiletest;

/**
 * 测试volatile是否能保证原子性
 */
public class Test {

    public volatile int inc = 0;

    public static void main(String[] args) {
        final Test test = new Test();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++)
                        test.increase();
                }
            }.start();
        }

        while (Thread.activeCount() > 1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }

    public void increase() {
        inc++;
    }
}
