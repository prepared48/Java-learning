package com.geniu.concurrent.javaConcurrencyInPractice;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * 通过interrupt中断线程
 *
 * @Author: zhongshibo
 * @Date: 2021/1/24 17:28
 */
public class Test0705CancelThread extends Thread {

    private final BlockingQueue<BigInteger> queue;

    public Test0705CancelThread(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()) {
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cancel() {
        interrupt();
    }
}
