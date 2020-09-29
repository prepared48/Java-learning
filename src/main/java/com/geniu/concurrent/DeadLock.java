package com.geniu.concurrent;

/**
 * boss笔试题：死锁例子
 *
 * @Author: zhongshibo
 * @Date: 2020/9/29 10:03
 */
public class DeadLock {

	public static Object t1 = new Object();
	public static Object t2 = new Object();

	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				synchronized (t1) {
					System.out.println("Thread1 t1");
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					}
					//	获取t2资源
					synchronized (t2) {
						System.out.println("Thread2 get t2");
					}
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				synchronized (t2) {
					System.out.println("Thread2 t2");
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					}
					//	获取t1资源
					synchronized (t1) {
						System.out.println("Thread2 get t1");
					}
				}
			}
		}.start();
	}
}
