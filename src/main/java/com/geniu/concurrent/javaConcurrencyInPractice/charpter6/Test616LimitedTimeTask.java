package com.geniu.concurrent.javaConcurrencyInPractice.charpter6;

import com.geniu.concurrent.javaConcurrencyInPractice.charpter7.FetchAdTask;
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

    private final ExecutorService executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1,
            Runtime.getRuntime().availableProcessors() + 1, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue(1000));
    ;

    public static void main(String[] args) {
        try {
            Test616LimitedTimeTask task = new Test616LimitedTimeTask();
            task.test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试超时取消
     *
     * @throws InterruptedException
     */
    void test() throws InterruptedException {
        Ad ad = null;
        long endNanos = System.nanoTime() + TIME_BUDGET;
        Future<Ad> f = executor.submit(new FetchAdTask());
        try {
            long timeLeft = endNanos - System.nanoTime();
            // 增加参数 超时时间和超时时间的单位
            ad = f.get(timeLeft, TimeUnit.NANOSECONDS);
        } catch (ExecutionException e) {
            ad = DEFAULT_AD;
        } catch (TimeoutException e) {
            // 超时，取消任务
            ad = DEFAULT_AD;
            System.out.println("超时取消");
            f.cancel(true);
        }
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
