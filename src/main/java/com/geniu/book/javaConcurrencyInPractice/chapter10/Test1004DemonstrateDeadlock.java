package com.geniu.book.javaConcurrencyInPractice.chapter10;

import javax.naming.InsufficientResourcesException;
import java.util.Random;

/**
 * 在典型条件下会发生死锁的循环
 *
 * @Author: zhongshibo
 * @Date: 2021/2/25 09:40
 */
public class Test1004DemonstrateDeadlock {

    private static final int NUM_THREADS = 20;

    private static final int NUM_ACCOUNTS = 5;

    private static final int NUM_ITERATIONS = 1000000;

    public static void main(String[] args) {
        final Random rnd = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(10000);
        }

        class TransferThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int amount = rnd.nextInt(1000);
                    transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }

    private static void transferMoney(Account fromAcct, Account toAcct, int amount) {
        synchronized (fromAcct) {
            synchronized (toAcct) {
                if (fromAcct.getBalance() - amount < 0) {
                    try {
                        throw new InsufficientResourcesException();
                    } catch (InsufficientResourcesException e) {
                        e.printStackTrace();
                    }
                } else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }
    }

}
