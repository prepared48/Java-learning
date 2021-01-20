package com.geniu.concurrent.javaConcurrencyInPractice;

import java.util.concurrent.Callable;

/**
 * @Author: zhongshibo
 * @Date: 2021/1/20 22:50
 */
public class FetchAdTask implements Callable<Ad> {

    @Override
    public Ad call() throws Exception {
        System.out.println("fetch task");
        return null;
    }
}
