package com.geniu.concurrent.ThreadPool;

/**
 * @Author: zhongshibo
 * @Date: 2020/11/3 17:50
 */
public class SubThread implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}
