package com.geniu.concurrent.vector;

import java.util.Vector;

/**
 * 对Vector线程安全的测试
 * <p>
 * 会报错：Exception in thread "Thread-2895927" java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 13
 * at java.util.Vector.get(Vector.java:753)
 * at com.geniu.concurrent.vector.TestVector$2.run(TestVector.java:34)
 * at java.lang.Thread.run(Thread.java:748)
 * <p>
 * 线程删除的时候，同时另一个线程去获取对应的值，就会报错
 * 所以，Vector 不是绝对的线程安全
 *
 * @Author: zhongshibo
 * @Date: 2021/3/28 17:09
 */
public class TestVector {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            });

            Thread printThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        System.out.println(vector.get(i));
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
