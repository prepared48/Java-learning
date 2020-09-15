package com.geniu.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public class SafeWM {

	// 库存上限
	private final AtomicLong upper = new AtomicLong(0);

	// 库存下限
	private final AtomicLong lower = new AtomicLong(0);

	public static void main(String[] args) throws Exception {
		Thread thread1 = new MyThread1();
		thread1.setName("线程1-name");
		Thread thread2 = new MyThread1();

		thread1.run();
		thread2.run();

		System.out.println();
	}

	// 设置库存上限
	void setUpper(long v) {
		// 检查参数合法性
		if (v < lower.get()) {
			throw new IllegalArgumentException();
		}
		upper.set(v);
	}
	// 省略其他业务代码

	// 设置库存下限
	void setLower(long v) {
		// 检查参数合法性
		if (v > upper.get()) {
			throw new IllegalArgumentException();
		}
		lower.set(v);
	}

	private static class MyThread1 extends Thread {
		@Override
		public void run() {
			SafeWM safeWM = new SafeWM();
			safeWM.setLower(7);
		}
	}

	private static class MyThread2 extends Thread {

		@Override
		public void run() {
			SafeWM safeWM = new SafeWM();
			safeWM.setUpper(5);
		}
	}
}