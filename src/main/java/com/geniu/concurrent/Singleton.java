package com.geniu.concurrent;

/**
 * DCL 单例模式 demo
 *
 * @Author: zhongshibo
 * @Date: 2020/11/3 14:11
 */
public class Singleton {

	private volatile static Singleton singleton;

	private Singleton() {
	}

	public static Singleton getSingleton() {
		if (singleton == null) {
			synchronized (Singleton.class) {
				if (singleton == null) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}

}
