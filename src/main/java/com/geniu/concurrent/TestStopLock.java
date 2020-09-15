package com.geniu.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestStopLock {
	private static Lock lock = new ReentrantLock();

	public static void main(String[] args) throws Exception {
		Thread thread1 = new MyThread();
		thread1.setName("thread1");
		thread1.start();
		sleep(3);
		Thread thread2 = new MyThread();
		thread2.setName("thread2");
		thread2.start();
		sleep(3);
		//直接杀死
		System.out.println(nowTime() + " stop thread1");
		thread1.stop();
		//等待线程终止
		thread1.join();
		thread2.join();
	}

	private static void fun1() {
		lock.lock();
		try {
			System.out.println(nowTime() + Thread.currentThread().getName() + " start");
			sleep(30);
			System.out.println(nowTime() + Thread.currentThread().getName() + " end");
		} finally {
			System.out.println(nowTime() + Thread.currentThread().getName() + " unlock");
			lock.unlock();
		}
	}

	private static void sleep(long second) {
		try {
			TimeUnit.SECONDS.sleep(second);
		} catch (InterruptedException e) {
			//重置中断状态
			Thread.currentThread().interrupt();
		}
	}

	private static String nowTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(new Date()) + " ";
	}

	private static class MyThread extends Thread {
		@Override
		public void run() {
			fun1();
		}
	}
}