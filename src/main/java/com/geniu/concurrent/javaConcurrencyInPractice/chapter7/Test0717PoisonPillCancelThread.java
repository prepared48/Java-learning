package com.geniu.concurrent.javaConcurrencyInPractice.chapter7;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * 通过毒丸对象，来取消 生产者-消费者服务
 *
 * @Author: zhongshibo
 * @Date: 2021/1/26 22:35
 */
public class Test0717PoisonPillCancelThread {

    private static final File POSION = new File("");

    private final IndexerThread consumer = new IndexerThread();

    private final CrawlerThread producer = new CrawlerThread();

    private final BlockingQueue<File> queue;

    private final FileFilter fileFilter;

    private final File root;

    public Test0717PoisonPillCancelThread(BlockingQueue<File> queue, FileFilter fileFilter, File root) {
        this.queue = queue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }

    /**
     * 消费者
     */
    public class IndexerThread extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    File file = queue.take();
                    // 毒丸对象 停止消费者
                    if (file == POSION) {
                        break;
                    } else {
                        indexFile(file);
                    }
                }
            } catch (InterruptedException e) {
                // deal with exception
            } finally {

            }
        }

        private void indexFile(File file) {
        }

        private void crawl(File root) throws InterruptedException {

        }
    }

    /**
     * 生产者
     */
    public class CrawlerThread extends Thread {

        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                // deal with exception
            } finally {
                while (true) {
                    try {
                        // 队列中存入毒丸
                        queue.put(POSION);
                        break;
                    } catch (InterruptedException e1) {
                        // deal with exception
                    }
                }
            }
        }

        private void crawl(File root) throws InterruptedException {

        }
    }
}

