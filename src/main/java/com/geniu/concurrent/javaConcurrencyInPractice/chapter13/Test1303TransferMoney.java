package com.geniu.concurrent.javaConcurrencyInPractice.chapter13;

import com.geniu.concurrent.javaConcurrencyInPractice.chapter10.Account;

import javax.naming.InsufficientResourcesException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 通过 tryLock 来避免锁顺序死锁
 *
 * @Author: zhongshibo
 * @Date: 2021/3/3 21:30
 */
public class Test1303TransferMoney {

    public boolean transferMoney(Account fromAcct, Account toAcct, int amount, long timeout, TimeUnit unit)
            throws InsufficientResourcesException, InterruptedException {
        long fixedDelay = getFixedDelayComponentNanos(timeout, unit);
        long randomMod = getRandomDelayModulusNanos(timeout, unit);
        long stopTime = System.nanoTime() + unit.toNanos(timeout);

        while (true) {
            if (fromAcct.lock.tryLock()) {
                try {
                    if (toAcct.lock.tryLock()) {
                        try {
                            if (fromAcct.getBalance() < amount) {
                                throw new InsufficientResourcesException();
                            } else {
                                fromAcct.debit(amount);
                                toAcct.credit(amount);
                                return true;
                            }
                        } finally {
                            toAcct.lock.unlock();
                        }
                    }
                } finally {
                    fromAcct.lock.unlock();
                }
            }
            if (System.nanoTime() < stopTime) {
                return false;
            }
            Random rnd = new Random();
//            NANOSENONDS.sleep(fixedDelay + rnd.nextLong() % randomMod);
        }
    }

    private long getRandomDelayModulusNanos(long timeout, TimeUnit unit) {
        return 10;
    }

    private long getFixedDelayComponentNanos(long timeout, TimeUnit unit) {
        return 5;
    }
}
