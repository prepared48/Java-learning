package com.geniu.concurrent.javaConcurrencyInPractice.chapter8;

/**
 * 自定义的线程工厂
 * 创建自定义的线程基础类，定义线程名字
 *
 * @Author: zhongshibo
 * @Date: 2021/2/4 22:23
 */
public class Test0806MyThreadFactory {

    private final String poolName;

    public Test0806MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    public Thread newThread(Runnable runnable) {
        return new Test0807MyAppThread(runnable, poolName);
    }
}
