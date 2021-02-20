package com.geniu.concurrent.javaConcurrencyInPractice.chapter5;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * 桌面搜索应用程序中的生产者任务
 *
 * @Author: zhongshibo
 * @Date: 2021/2/20 20:52
 */
public class Test0508Indexer implements Runnable {

    private final BlockingQueue<File> queue;

    public Test0508Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                indexFile(queue.take());
            }
        } catch (InterruptedException e) {
            // 中断线程
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 给文件建立索引
     *
     * @param take
     */
    private void indexFile(File take) {
    }
}
