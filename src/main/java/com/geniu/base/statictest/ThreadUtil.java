package com.geniu.base.statictest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadUtil {

    private static Logger logger = LoggerFactory.getLogger(ThreadUtil.class);
    private static volatile ExecutorService executor;

    public ThreadUtil() {
    }

    public static Future<?> submit(Runnable task) {
        return executor.submit(task);
    }

    public static <V> Future<V> submit(Callable<V> task) {
        return executor.submit(task);
    }

    public static void execute(Runnable task) {
        try {
            executor.execute(task);
        } catch (Exception var2) {
            logger.error("task execute error", var2);
        }

    }

    public static <V> List<V> submit(List<Callable<V>> callables, long timeout) {
        new ArrayList();
        ArrayList result = new ArrayList();

        try {
            List<Future<V>> futures = executor.invokeAll(callables, timeout, TimeUnit.SECONDS);
            Iterator var5 = futures.iterator();

            while (var5.hasNext()) {
                Future<V> future = (Future) var5.next();
                V v = future.get();
                if (v != null) {
                    result.add(v);
                }
            }

            return result;
        } catch (Exception var8) {
            logger.error("Submit Error", var8);
            throw new RuntimeException("Submit Error");
        }
    }

    public static void shutdown() {
        if (null != executor) {
            executor.shutdown();
        }

    }

    private static void initExecutor() {
        if (executor == null) {
            Class var0 = ThreadUtil.class;
            synchronized (ThreadUtil.class) {
                if (executor == null) {
                    executor = new ThreadPoolExecutor(10, 15, 1L, TimeUnit.MINUTES, new ArrayBlockingQueue(1000), new ThreadUtil.DefaultThreadFactory());
                }
            }
        }

    }

    static {
        initExecutor();
    }

    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final ThreadGroup threadGroup;
        private final String namePrefix;

        public DefaultThreadFactory() {
            SecurityManager sm = System.getSecurityManager();
            this.threadGroup = sm != null ? sm.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(this.threadGroup, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }

            if (t.getPriority() != 5) {
                t.setPriority(5);
            }

            return t;
        }
    }
}
