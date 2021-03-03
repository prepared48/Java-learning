package com.geniu.concurrent.javaConcurrencyInPractice.chapter13;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 带有时间限制的加锁
 *
 * @Author: zhongshibo
 * @Date: 2021/3/3 21:45
 */
public class Test1304TimeLimitLock {

    ReentrantLock lock = new ReentrantLock();

    public boolean trySendOnSharedLine(String message, long timeout, TimeUnit unit) throws InterruptedException {

        long nanosLock = unit.toNanos(timeout) - estimatedNanosToSend(message);
        if (!lock.tryLock(nanosLock, TimeUnit.NANOSECONDS)) {
            return false;
        }
        try {
            return sendOnSharedLine(message);
        } finally {
            lock.unlock();
        }

    }

    private boolean sendOnSharedLine(String message) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            return cancellableSendOnSharedLine(message);
        } finally {
            lock.unlock();
        }
    }

    private boolean cancellableSendOnSharedLine(String message) throws InterruptedException {
        return true;
    }

    private long estimatedNanosToSend(String message) {
        return 10;
    }
}
