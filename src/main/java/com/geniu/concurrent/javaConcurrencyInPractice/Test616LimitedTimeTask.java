package com.geniu.concurrent.javaConcurrencyInPractice;

import sun.jvm.hotspot.debugger.Page;

import java.util.concurrent.*;

/**
 * Future 为任务设置时间，超时取消
 *
 * @Author: zhongshibo
 * @Date: 2021/1/20 22:46
 */
public class Test616LimitedTimeTask {

    private static final long TIME_BUDGET = 100L;
    private static final Ad DEFAULT_AD = new Ad();

    private final ExecutorService executor;

    // 构造函数初始化线程池
    public Test616LimitedTimeTask(ExecutorService executor) {
        this.executor = executor;
    }

    Page renderPageWithAd() throws InterruptedException {
        Ad ad = null;
        long endNanos = System.nanoTime() + TIME_BUDGET;
        Future<Ad> f = executor.submit(new FetchAdTask());
        // P
        Page page = readerPageBody();
        try {
            long timeLeft = endNanos - System.nanoTime();
            // 增加参数 超时时间和超时时间的单位
            f.get(timeLeft, TimeUnit.NANOSECONDS);
        } catch (ExecutionException e) {
            ad = DEFAULT_AD;
        } catch (TimeoutException e) {
            // 超时，取消任务
            ad = DEFAULT_AD;
            f.cancel(true);
        }
        return null;
    }

    private Page readerPageBody() {
        return null;
    }
}
