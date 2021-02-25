package com.geniu.concurrent.javaConcurrencyInPractice.chapter10;

import javax.naming.InsufficientResourcesException;

/**
 * 错误示例：动态的锁顺序死锁
 * 可能发生死锁的场景：一个线程从X向Y转账，另一个线程从Y向X转账
 *
 * @Author: zhongshibo
 * @Date: 2021/2/25 09:19
 */
public class Test1002TransferMoney {

    public void transferMoney(Account fromAccount, Account toAccount, int amount)
            throws InsufficientResourcesException {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance() - amount < 0) {
                    throw new InsufficientResourcesException();
                } else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }
}
