package com.geniu.base.statictest;

import static java.lang.Thread.sleep;

/**
 * @Author: zhongshibo
 * @Date: 2021/7/8 16:23
 */
public class ThreadUtilTest {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            ThreadUtil.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        sleep(100);
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        ThreadUtil.shutdown();
    }
}
