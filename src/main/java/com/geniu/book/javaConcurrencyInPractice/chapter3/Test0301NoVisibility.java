package com.geniu.book.javaConcurrencyInPractice.chapter3;

/**
 * 错误示范：在没有同步的情况下共享变量
 *
 * @Author: zhongshibo
 * @Date: 2021/2/22 17:43
 */
public class Test0301NoVisibility {

    private static boolean ready;

    private static int number;

    private static class ReaderThread extends Thread {

        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
