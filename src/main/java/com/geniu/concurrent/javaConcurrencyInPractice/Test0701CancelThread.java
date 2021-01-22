package com.geniu.concurrent.javaConcurrencyInPractice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 如何优雅的停止线程
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
