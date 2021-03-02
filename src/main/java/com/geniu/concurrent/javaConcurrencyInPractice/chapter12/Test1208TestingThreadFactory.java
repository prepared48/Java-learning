package com.geniu.concurrent.javaConcurrencyInPractice.chapter12;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试 ThreadFactory 的线程工厂类
 *
 * @Author: zhongshibo
 * @Date: 2021/3/2 22:27
 */
public class Test1208TestingThreadFactory implements ThreadFactory {

    public final AtomicInteger numCreated = new AtomicInteger();

    private final ThreadFactory factory = Executors.defaultThreadFactory();

    @Override
    public Thread newThread(Runnable r) {
        numCreated.incrementAndGet();
        return factory.newThread(r);
    }
}
