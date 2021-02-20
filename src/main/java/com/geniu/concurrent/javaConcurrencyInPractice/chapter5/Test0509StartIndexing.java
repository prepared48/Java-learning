package com.geniu.concurrent.javaConcurrencyInPractice.chapter5;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author: zhongshibo
 * @Date: 2021/2/20 20:55
 */
public class Test0509StartIndexing {
    /**
     * 阻塞队列大小
     */
    private static final int BOUND = 100;
    /**
     * 消费者数量
     */
    private static final int N_CONSUMERS = 5;

    public static void main(String[] args) {

    }

    public static void startIndexing(File[] roots) {
        // 阻塞队列
        BlockingQueue<File> queue = new LinkedBlockingDeque<>(BOUND);
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };
        // 生产者 启动
        for (File root : roots) {
            new Thread(new Test0508FileCrawler(queue, filter, root)).start();
        }
        // 消费者 启动
        for (int i = 0; i < N_CONSUMERS; i++) {
            new Thread(new Test0508Indexer(queue)).start();
        }
    }

}
