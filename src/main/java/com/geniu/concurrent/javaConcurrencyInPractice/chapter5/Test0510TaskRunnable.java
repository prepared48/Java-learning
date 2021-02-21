package com.geniu.concurrent.javaConcurrencyInPractice.chapter5;

import javafx.concurrent.Task;

import java.util.concurrent.BlockingQueue;

/**
 * 恢复中断状态以避免屏蔽中断
 *
 * @Author: zhongshibo
 * @Date: 2021/2/21 22:27
 */
public class Test0510TaskRunnable implements Runnable {

    BlockingQueue<Task> queue;

    @Override
    public void run() {
        try {
            processTask(queue.take());
        } catch (InterruptedException e) {
            // 恢复被中断的状态
            Thread.currentThread().interrupt();
        }
    }

    private void processTask(Task take) {
    }
}
