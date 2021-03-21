package com.geniu.book.thinkinginjava.chapter21;

/**
 * @Author: zhongshibo
 * @Date: 2021/3/21 21:41
 */
public class Practice1Runnable implements Runnable {

    public Practice1Runnable() {
        System.out.println("start practice ");
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ", practice run 1 ...");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + ", practice run 2 ...");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + ", practice run 3...");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + ", end practice");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Practice1Runnable()).start();
        }
        System.out.println("practice end");
    }
}
