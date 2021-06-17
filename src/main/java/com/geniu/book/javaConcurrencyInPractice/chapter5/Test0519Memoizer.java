package com.geniu.book.javaConcurrencyInPractice.chapter5;

import org.apache.commons.lang3.concurrent.Computable;

import java.util.concurrent.*;

/**
 * Memoizer 的最终实现
 *
 * @Author: zhongshibo
 * @Date: 2021/2/22 13:58
 */
public class Test0519Memoizer<A, V> implements Computable<A, V> {

    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Test0519Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            // 如果没有任务在计算它（arg），则启动一个FutureTask来计算
            if (f == null) {
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws InterruptedException {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(eval);
                // 防止同时有两个人任务在计算一个值
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                f.get();
            } catch (CancellationException e) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                // 处理get返回的各种异常情况。可以参考 Test0512Preloader 类中的 launderThrowable 方法
                e.printStackTrace();
            }
        }
    }
}
