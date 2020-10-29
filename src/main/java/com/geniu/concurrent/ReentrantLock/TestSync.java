//package com.geniu.concurrent.ReentrantLock;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * 测试线程间通信
// */
//public class TestSync {
//
//	public static void main(String[] args) {
//		ReentrantLock lock = new ReentrantLock();
//		Condition condition = lock.newCondition();
//		List<String> list = new ArrayList<>();
//		// 实现线程A
//		Thread threadA = new Thread(() -> {
//			lock.lock();
//			for (int i = 1; i <= 10; i++) {
//				list.add("abc");
//				System.out.println("线程A向list中添加一个元素，此时list中的元素个数为：" + list.size());
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				if (list.size() == 5)
//					condition.signal();
//
//			}
//			lock.unlock();
//		});
//		// 实现线程B
//		Thread threadB = new Thread(() -> {
//			lock.lock();
//			if (list.size() != 5) {
//				try {
//					//  等待
//					condition.await();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			System.out.println("线程B收到通知，开始执行自己的业务...");
//			lock.unlock();
//		});
//		threadB.start();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		threadA.start();
//	}
//}
