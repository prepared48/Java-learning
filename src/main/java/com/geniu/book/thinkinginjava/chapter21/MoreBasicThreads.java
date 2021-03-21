package com.geniu.book.thinkinginjava.chapter21;

/**
 * 测试创建多个不同的任务
 *
 * @Author: zhongshibo
 * @Date: 2021/3/21 21:35
 */
public class MoreBasicThreads {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waitint for LiftOff");
    }
}
