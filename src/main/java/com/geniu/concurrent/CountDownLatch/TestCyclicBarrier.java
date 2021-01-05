package com.geniu.concurrent.CountDownLatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

/**
 * 循环执行任务
 * 测试 CyclicBarrier
 * 两个任务比较耗时：查询运单库、查询订单哭
 * 需要执行这两个任务获取返回数据后执行check方法；
 * <p>
 * 这里的优化点：执行check的时候，查询下一次的两个耗时任务：查询运单库、查询订单哭
 *
 * @Author: zhongshibo
 * @Date: 2021/1/5 14:05
 */
public class TestCyclicBarrier {
    // 执行回调的线程池
    Executor executor = Executors.newFixedThreadPool(1);
    final CyclicBarrier barrier = new CyclicBarrier(2, () -> {
        executor.execute(() -> check());
    });

    public static void main(String[] args) {
        TestCyclicBarrier barrier = new TestCyclicBarrier();
        barrier.checkAll();
    }

    void check() {
        System.out.println(">>>> check");
    }

    void checkAll() {
        // 循环查询订单库
        Thread T1 = new Thread(() -> {
            while (true) {
                try {
                    // 查询订单库
                    sleep(1000);
                    System.out.println("查询订单哭");
                    // 等待
                    barrier.await();
                    System.out.println(">> count = " + barrier.getNumberWaiting());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        T1.start();
        // 循环查询运单库
        Thread T2 = new Thread(() -> {
            while (true) {
                try {
                    // 查询订单库
                    System.out.println("查询运单库");
                    // 查询订单库
                    sleep(2000);
                    // 等待
                    barrier.await();
                    System.out.println(">> count = " + barrier.getNumberWaiting());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        T2.start();
    }
}
