package com.geniu.concurrent.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zhongshibo
 * @Date: 2020/9/24 08:30
 */
public class TestCondition {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		//	获取condition的方法
		Condition condition = lock.newCondition();

	}

	public void conditionWait(Lock lock, Condition condition) throws InterruptedException {
		lock.lock();
		try {
			condition.await();
		} finally {
			lock.unlock();
		}
	}

	public void conditionSignal(Lock lock, Condition condition) throws InterruptedException {
		lock.lock();
		try {
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
}
