package com.geniu.book.javaConcurrencyInPractice.chapter10;

import javax.naming.InsufficientResourcesException;

/**
 * 通过锁顺序来避免死锁
 *
 * @Author: zhongshibo
 * @Date: 2021/2/25 09:25
 */
public class Test1003TransferMoney {

    // 加时赛 锁
    private static final Object tieLock = new Object();

    public void transferMoney(final Account fromAcct,
                              final Account toAcct,
                              final int amount) throws InsufficientResourcesException {
        class Helper {
            public void transfer() throws InsufficientResourcesException {
                if (fromAcct.getBalance() - amount < 0) {
                    throw new InsufficientResourcesException();
                } else {
                    // 转账
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }

        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);

        // 固定锁顺序，hash值小的先锁住
        if (fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {
            // 如果hash值相等，先锁住tieLock ？？？？
            synchronized (tieLock) {
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }


    }
}
