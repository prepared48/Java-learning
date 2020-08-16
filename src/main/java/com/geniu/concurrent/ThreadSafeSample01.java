package com.geniu.concurrent;

/**
 * 原子性问题说明，两个线程的并发，也不能保证原子性
 * 在两次取值的过程中，其他线程可能已经修改了 sharedState。
 *
 * @Author: zhongshibo
 * @Date: 2020/7/24 06:58
 */
public class ThreadSafeSample01 {
    public int sharedState;
    public void nonSafeAction() {
        while (sharedState < 100000) {
            int former = sharedState++;
            int latter = sharedState;
            if (former != latter - 1) {
                System.out.printf("Observed data race, former is " +
                        former + ", " + "latter is " + latter);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeSample01 sample = new ThreadSafeSample01();
        Thread threadA = new Thread(){
            public void run(){
                sample.nonSafeAction();
            }
        };
        Thread threadB = new Thread(){
            public void run(){
                sample.nonSafeAction();
            }
        };
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}