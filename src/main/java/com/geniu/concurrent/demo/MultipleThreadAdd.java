package com.geniu.concurrent.demo;

/**
 * 测试原子性：1W个线程执行value++操作
 * 必须要有加锁的get方法，才能保证结果的正确性
 * 说明：管程中锁的规则，是只保证后续对这个锁的加锁的可见性，而 get() 方法并没有加锁操作，所以可见性没法保证
 *
 * @Author: zhongshibo
 * @Date: 2021/3/12 09:35
 */
public class MultipleThreadAdd {

    private int value = 0;

    public static void main(String[] args) {
        MultipleThreadAdd threadAdd = new MultipleThreadAdd();
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    threadAdd.add();
                }
            };
            thread.start();
        }
        System.out.println(threadAdd.get());
    }

    public synchronized int get() {
        return value;
    }

    public synchronized void add() {
        value++;
    }
}
