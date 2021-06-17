package com.geniu.book.javaConcurrencyInPractice.chapter8;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 在单线程Executor中任务发生死锁
 *
 * @Author: zhongshibo
 * @Date: 2021/1/27 22:54
 */
public class Test0801DeadLock {

    ExecutorService exec = Executors.newSingleThreadExecutor();

    public class RenderPageTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("header.html"));
            String page = renderBody();
            return header.get() + page + footer.get();
        }
    }

    private String renderBody() {
        return "body.html";
    }
}
