package com.geniu.concurrent.ThreadPool;

/**
 * 线程
 */
public class ThreadTest implements Runnable {

	@Override
	public void run() {
		try {
			// 让线程不会马上结束
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}