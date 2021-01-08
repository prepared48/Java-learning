package com.geniu.concurrent.completedFuture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.lang.Thread.sleep;

/**
 * CompletableFuture
 * <p>
 * 默认情况下 CompletableFuture 会使用公共的 ForkJoinPool 线程池，这个线程池默认创建的线程数是 CPU 的核数
 * （也可以通过 JVM option:-Djava.util.concurrent.ForkJoinPool.common.parallelism 来设置 ForkJoinPool 线程池的线程数）。
 *
 * @Author: zhongshibo
 * @Date: 2021/1/7 18:06
 */
public class TestCompletableFuture {

    public static void main(String[] args) {
        System.out.println("启动线程数：" + (((Runtime.getRuntime().availableProcessors() - 1) > 1)
                ? (Runtime.getRuntime().availableProcessors() - 1) : "实际任务数"));
        System.out.println("start： " + new Date());
        CompletableFuture<Integer> task4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务4, 线程名字" + Thread.currentThread().getName());
            try {
                sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3;
        });
        CompletableFuture<List<Integer>> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1, 线程名字" + Thread.currentThread().getName());
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        });
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2, 线程名字" + Thread.currentThread().getName());
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        });
        CompletableFuture<Integer> task3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务3, 线程名字" + Thread.currentThread().getName());
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3;
        });

        CompletableFuture.allOf(task1, task2, task3, task4);
        System.out.println("end: " + new Date());
        try {
            task1.get();
            System.out.println("task1 end: " + new Date());
            task2.get();
            System.out.println("task2 end: " + new Date());
            task3.get();
            System.out.println("task3 end: " + new Date());
            task4.get();
            System.out.println("task4 end: " + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
