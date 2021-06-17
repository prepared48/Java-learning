package com.geniu.book.javaConcurrencyInPractice.chapter10;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zhongshibo
 * @Date: 2021/2/25 09:20
 */
public class Account {

    public ReentrantLock lock;

    private int balance;

    public Account() {
    }

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void debit(int amount) {
        // 扣除余额
    }

    public void credit(int amount) {
        // 增加余额
    }
}
