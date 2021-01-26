package com.geniu.concurrent.javaConcurrencyInPractice;

import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 关闭 ExecutorService
 * 通过调用 ExecutorService 的 shutdown() 方法。更安全。
 *
 * @Author: zhongshibo
 * @Date: 2021/1/26 22:19
 */
public class Test0716CloseExecutorService {

    private static final long TIMEOUT = 1000L;
    private static final TimeUnit UNIT = TimeUnit.MILLISECONDS;
    private final ExecutorService exec = Executors.newScheduledThreadPool(5);

    private final PrintWriter writer;

    public Test0716CloseExecutorService(PrintWriter writer) {
        this.writer = writer;
    }

    public void start() {
    }

    public void stop() throws InterruptedException {
        try {
            exec.shutdown();
            // TODO 问：为什么停止线程，需要调用以下语句
            // 这个方法就是调用shutdown() 之后等待任务执行完毕的方法，可以查看源码的注释
            // Blocks until all tasks have completed execution after a shutdown
            // request, or the timeout occurs, or the current thread is
            // interrupted, whichever happens first.
            exec.awaitTermination(TIMEOUT, UNIT);
        } finally {
            writer.close();
        }
    }

    public void log(String msg) {
        try {
            exec.execute(new WriteTask(msg));
        } catch (RejectedExecutionException ignored) {

        }
    }
}
