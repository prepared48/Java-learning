package com.geniu.concurrent.javaConcurrencyInPractice.chapter7;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 中断线程
 * 存在join的不足：不知道执行控制是因为线程正常退出而返回还是因为join超时而返回
 *
 * @Author: zhongshibo
 * @Date: 2021/1/25 21:49
 */
public class Test0709InterruptThread {

    /**
     * 初始化Scheduled线程池
     */
    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(5);

    public static void timeRun(final Runnable r, long timeout, TimeUnit unit) {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() throws Exception {
                if (t != null) {
                    throw launderThrowable(t);
                }
            }

            private Exception launderThrowable(Throwable t) {
                return null;
            }
        }
        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
        // timeout unit时间范围内中断线程
        cancelExec.schedule((Runnable) () -> {
            taskThread.interrupt();
        }, timeout, unit);
        try {
            // 等待线程执行 timeout 单位时间
            taskThread.join(unit.toMillis(timeout));
            task.rethrow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
