package com.geniu.book.thinkinginjava.chapter21;

/**
 * 创建一个线程
 * <p>
 * yield() 作用是：可以在当前位置通知其他线程执行
 *
 * @Author: zhongshibo
 * @Date: 2021/3/21 21:29
 */
public class LiftOff implements Runnable {

    // default
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "), ";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
    }
}
