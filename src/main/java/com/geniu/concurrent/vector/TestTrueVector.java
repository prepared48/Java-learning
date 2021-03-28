package com.geniu.concurrent.vector;

import java.util.Vector;

/**
 * 对Vector线程安全的测试
 * Vector是相对的线程安全，保证单个操作是线程安全的；对于特定顺序的连续调用，还需要调用方保证正确性
 *
 * @Author: zhongshibo
 * @Date: 2021/3/28 17:09
 */
public class TestTrueVector {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    synchronized (vector) {
                        for (int i = 0; i < vector.size(); i++) {
                            vector.remove(i);
                        }
                    }
                }
            });

            Thread printThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (vector) {
                        for (int i = 0; i < vector.size(); i++) {
                            System.out.println(vector.get(i));
                        }
                    }
                }
            });

            removeThread.start();
            printThread.start();

            while (Thread.activeCount() > 20) {
                break;
            }
        }
    }
}
