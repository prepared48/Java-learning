package com.geniu.book.thinkinginjava.chapter21;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程（daemon）
 * daemon: 不属于程序中不可或缺的部分。因此当所有的非后台线程结束时，程序也就终止了
 *
 * @Author: zhongshibo
 * @Date: 2021/4/5 22:50
 */
public class SimpleDaemons implements Runnable {

    Logger logger = LoggerFactory.getLogger(SimpleDaemons.class);

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MICROSECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
//            logger.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            // 必须在 start() 之前
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons started");
        try {
            TimeUnit.MICROSECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
