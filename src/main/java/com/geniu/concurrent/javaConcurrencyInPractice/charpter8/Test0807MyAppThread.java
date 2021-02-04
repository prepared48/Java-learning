package com.geniu.concurrent.javaConcurrencyInPractice.charpter8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 定制Thread基类
 *
 * @Author: zhongshibo
 * @Date: 2021/2/4 22:24
 */
public class Test0807MyAppThread extends Thread {

    public static final String DEFAULT_NAME = "MyAppThread";

    private static volatile boolean debugLifecycle = false;

    private static final AtomicInteger created = new AtomicInteger();

    private static final AtomicInteger alive = new AtomicInteger();

    private static final Logger log = Logger.getAnonymousLogger();

    public Test0807MyAppThread(Runnable r) {
        this(r, DEFAULT_NAME);
    }

    public Test0807MyAppThread(Runnable runnable, String name) {
        super(runnable, name + "-" + created.incrementAndGet());

        setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                log.log(Level.SEVERE, "UNCAUGHT in thread " + t.getName(), e);
            }
        });

    }

    @Override
    public void run() {
        boolean debug = debugLifecycle;
        if (debug) {
            log.log(Level.FINE, "created " + getName());
        }
        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if (debug) {
                log.log(Level.FINE, "Existing " + getName());
            }
        }
    }

    public static int getThreadCreated() {
        return created.get();
    }

    public static int getThreadAlive() {
        return alive.get();
    }

    public static boolean getDebug() {
        return debugLifecycle;
    }

    public static void setDebug(boolean b) {
        debugLifecycle = b;
    }
}
