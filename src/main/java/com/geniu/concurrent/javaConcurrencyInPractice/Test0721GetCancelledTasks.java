package com.geniu.concurrent.javaConcurrencyInPractice;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 关闭线程池之后，获取被取消的任务清单
 *
 * @Author: zhongshibo
 * @Date: 2021/1/26 22:54
 */
public class Test0721GetCancelledTasks extends AbstractExecutorService {

    private final ExecutorService exec;

    // 被取消的任务清单
    private final Set<Runnable> tasksCancelledAtShutdown = Collections.synchronizedSet(new HashSet<>());

    public Test0721GetCancelledTasks(ExecutorService exec) {
        this.exec = exec;
    }

    @Override
    public void execute(final Runnable runnable) {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } finally {
                    if (isShutdown() && Thread.currentThread().isInterrupted()) {
                        // 保存被中断或者关闭的任务
                        tasksCancelledAtShutdown.add(runnable);
                    }
                }

            }
        });
    }

    @Override
    public void shutdown() {
    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }


}
