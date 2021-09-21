package com.geniu.concurrent.thread;


public class PrintABC2 {
    // 使用布尔变量对打印顺序进行控制，true表示轮到当前线程打印
    private static boolean startA = true;
    private static boolean startB = false;
    private static boolean startC = false;

    public static void main(String[] args) {
        // 作为锁对象
        final Object o = new Object();
        // A线程
        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 10; ) {
                    if (startA) {
                        // 代表轮到当前线程打印
                        System.out.print(Thread.currentThread().getName());
                        // 下一个轮到B打印，所以把startB置为true，其它为false
                        startA = false;
                        startB = true;
                        startC = false;
                        // 唤醒其他线程
                        o.notifyAll();
                        // 在这里对i进行增加操作
                        i++;
                    } else {
                        // 说明没有轮到当前线程打印，继续wait
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "A").start();
        // B线程
        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 10; ) {
                    if (startB) {
                        System.out.print(Thread.currentThread().getName());
                        startA = false;
                        startB = false;
                        startC = true;
                        o.notifyAll();
                        i++;
                    } else {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "B").start();
        // C线程
        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 10; ) {
                    if (startC) {
                        System.out.print(Thread.currentThread().getName());
                        startA = true;
                        startB = false;
                        startC = false;
                        o.notifyAll();
                        i++;
                    } else {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "C").start();
    }
}
