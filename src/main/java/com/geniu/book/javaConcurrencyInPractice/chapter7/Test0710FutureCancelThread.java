package com.geniu.book.javaConcurrencyInPractice.chapter7;

import java.util.concurrent.*;

/**
 * 取消线程的正确姿势：通过Future来取消线程
 * 当Future.get抛出 InterruptedException或者TimeoutException时，如果你知道不再需要结果，
 * 那么就可以调用Future.cancel来取消任务
 *
 * @Author: zhongshibo
 * @Date: 2021/1/25 22:08
 */
public class Test0710FutureCancelThread {

    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(5);

    public static void timeRun(final Runnable r, long timeout, TimeUnit unit) {
        Future<?> task = cancelExec.submit(r);
        try {
            task.get();
        } catch (InterruptedException e) {
            // 取消线程
            task.cancel(true);
        } catch (ExecutionException e) {
            // 如果任务已经执行完毕，那么执行取消不会有什么影响
            // 如果任务正在执行，那么将被中断
            task.cancel(true);
        }
    }
}
