package com.geniu.concurrent.javaConcurrencyInPractice.charpter8;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * 使用信号量 Semaphore 控制任务的提交速率
 *
 * @Author: zhongshibo
 * @Date: 2021/2/4 22:12
 */
public class Test0804SemaphoreThreadPool {

    private final Executor exec;

    private final Semaphore semaphore;

    public Test0804SemaphoreThreadPool(Executor exec, Semaphore semaphore) {
        this.exec = exec;
        this.semaphore = semaphore;
    }

    public void submitTask(final Runnable command) throws InterruptedException {
        // 消费一个许可（permit）
        semaphore.acquire();
        try {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        command.run();
                    } finally {
                        // 创建一个许可（permit）
                        semaphore.release();
                    }

                }
            });
        } catch (RejectedExecutionException e) {
            // 创建一个许可（permit）
            semaphore.release();
        }

    }
}
