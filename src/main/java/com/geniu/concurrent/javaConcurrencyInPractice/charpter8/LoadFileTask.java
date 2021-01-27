package com.geniu.concurrent.javaConcurrencyInPractice.charpter8;

import java.util.concurrent.Callable;

/**
 * charpter8 加载文件任务
 *
 * @Author: zhongshibo
 * @Date: 2021/1/27 22:56
 */
public class LoadFileTask implements Callable<String> {

    private String str;

    public LoadFileTask(String str) {
        this.str = str;
    }

    @Override
    public String call() throws Exception {
        return str;
    }
}
