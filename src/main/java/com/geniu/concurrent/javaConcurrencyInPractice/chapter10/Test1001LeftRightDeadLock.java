package com.geniu.concurrent.javaConcurrencyInPractice.chapter10;

/**
 * 错误示例：简单的饿锁顺序死锁
 * 容易发生死锁
 *
 * @Author: zhongshibo
 * @Date: 2021/2/25 09:14
 */
public class Test1001LeftRightDeadLock {

    private final Object left = new Object();

    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            doSomething();
        }
    }

    private void doSomething() {
    }

    private void doSomethingElse() {
    }

    public void rightLeft() {
        synchronized (right) {
            doSomethingElse();
        }
    }
}
