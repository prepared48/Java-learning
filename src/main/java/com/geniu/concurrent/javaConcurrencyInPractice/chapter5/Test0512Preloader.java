package com.geniu.concurrent.javaConcurrencyInPractice.chapter5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用 FutureTask 来提前加载稍后需要的数据
 * <p>
 * 提前加载数据
 * future.get() 的异常处理需要注意
 *
 * @Author: zhongshibo
 * @Date: 2021/2/21 23:04
 */
public class Test0512Preloader {

    private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws DataLoadException {
            return loadProductInfo();
        }
    });

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException) {
                throw (DataLoadException) cause;
            } else {
                throw launderThrowable(cause);
            }
        }
    }

    /**
     * 异常处理封装方法
     *
     * @param t
     * @return
     */
    private RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("Not unchecked", t);
        }
    }

    public ProductInfo loadProductInfo() {
        return new ProductInfo();
    }
}
