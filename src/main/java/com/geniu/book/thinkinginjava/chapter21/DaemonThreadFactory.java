package com.geniu.book.thinkinginjava.chapter21;

import java.util.concurrent.ThreadFactory;

/**
 * 后台线程工厂
 *
 * @Author: zhongshibo
 * @Date: 2021/4/5 22:58
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
