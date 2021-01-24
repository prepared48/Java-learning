package com.geniu.concurrent.javaConcurrencyInPractice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 如何优雅的停止线程
 * cancel标志，如果调用阻塞方法，可能会导致永远也不会检查取消标志，导致线程不能被终止
 * ==》中断是实现取消的最合理方式
 *
 * @Author: zhongshibo
 * @Date: 2021/1/21 21:56
 */
public class Test0701CancelThread implements Runnable {

    public static void main(String[] args) {
        Test0701CancelThread cancelThread = new Test0701CancelThread();
        try {
            cancelThread.aSecondOfPrimes();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private final List<BigInteger> primes = new ArrayList<>();

    private volatile boolean cancelled;

    @Override
    public void run() {
        System.out.println("执行线程");
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        System.out.println("取消线程");
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<>(primes);
    }

    /**
     * 测试方法
     * 素数生成器执行1s之后停止
     *
     * @return
     * @throws InterruptedException
     */
    public List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        Test0701CancelThread cancelThread = new Test0701CancelThread();
        new Thread(cancelThread).start();
        try {
            SECONDS.sleep(1);
        } finally {
            cancelThread.cancel();
        }
        return cancelThread.get();
    }
}
