package com.geniu.concurrent.javaConcurrencyInPractice.chapter7;

/**
 * 典型的线程池工作者线程结构
 *
 * @Author: zhongshibo
 * @Date: 2021/1/27 22:02
 */
public class Test0723ClassicalThreadPool extends Thread {

    @Override
    public void run() {
        Throwable thrown = null;
        try {
            while (!isInterrupted()) {
                runTask(getTaskFromWorkQueue());
            }
        } catch (Throwable e) {
            thrown = e;
        } finally {
            threadExited(this, thrown);
        }
    }

    private void runTask(Thread taskFromWorkQueue) {
    }

    private void threadExited(Test0723ClassicalThreadPool test0723ClassicalThreadPool, Throwable thrown) {
    }

    public Thread getTaskFromWorkQueue() {
        return new Thread();
    }
}
