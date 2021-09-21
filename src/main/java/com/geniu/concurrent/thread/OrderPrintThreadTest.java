package com.geniu.concurrent.thread;

import java.util.concurrent.locks.LockSupport;

public class OrderPrintThreadTest {

    static Thread threadA, threadB, threadC;



    public static void main(String[] args) {
//        threadA = new Thread(()->{
//            for(int i = 0; i < 10; i++) {
//                System.out.print(Thread.currentThread().getName());
//                LockSupport.unpark(threadB);
//                LockSupport.park();
//            }
//        }, "A");
//
//        threadB = new Thread(()->{
//            for(int i = 0; i < 10; i++) {
//                LockSupport.park();
//                System.out.print(Thread.currentThread().getName());
//                LockSupport.unpark(threadC);
//
//            }
//        }, "B");
//
//        threadC = new Thread(()->{
//            for(int i = 0; i < 10; i++) {
//                LockSupport.park();
//                System.out.print(Thread.currentThread().getName());
//                LockSupport.unpark(threadA);
//
//            }
//        }, "C");
//        threadA.start();
//        threadB.start();
//        threadC.start();


        threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                // 打印当前线程名称
                System.out.print(Thread.currentThread().getName());
                // 唤醒下一个线程
                LockSupport.unpark(threadB);
                // 当前线程阻塞
                LockSupport.park();
            }
        }, "A");
        threadB = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                // 先阻塞等待被唤醒
                LockSupport.park();
                System.out.print(Thread.currentThread().getName());
                // 唤醒下一个线程
                LockSupport.unpark(threadC);
            }
        }, "B");
        threadC = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                // 先阻塞等待被唤醒
                LockSupport.park();
                System.out.print(Thread.currentThread().getName());
                // 唤醒下一个线程
                LockSupport.unpark(threadA);
            }
        }, "C");
        threadA.start();
        threadB.start();
        threadC.start();
    }

}
