package com.geniu.concurrent.thread;

import static java.lang.Thread.sleep;

/**
 * 验证频繁创建线程的问题
 * 不会报错，只是消耗资源
 *
 * @Author: zhongshibo
 * @Date: 2021/1/19 22:51
 */
public class TestNewThread {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            new Thread(task).start();
        }
    }
}
