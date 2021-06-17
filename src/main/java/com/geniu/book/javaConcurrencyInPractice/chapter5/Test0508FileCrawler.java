package com.geniu.book.javaConcurrencyInPractice.chapter5;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * 桌面搜索应用程序中的生产者任务
 *
 * @Author: zhongshibo
 * @Date: 2021/2/20 20:47
 */
public class Test0508FileCrawler implements Runnable {

    private final BlockingQueue<File> fileQueue;

    private final FileFilter fileFilter;

    private final File root;

    public Test0508FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            // 中断线程
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(fileFilter);
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(root);
                } else if (!alreadyIndexed(entry)) {
                    fileQueue.put(entry);
                }
            }
        }
    }

    /**
     * 判断是否已经被建索引
     *
     * @param entry
     * @return
     */
    private boolean alreadyIndexed(File entry) {
        return false;
    }
}
