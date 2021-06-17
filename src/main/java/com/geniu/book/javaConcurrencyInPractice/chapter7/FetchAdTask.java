package com.geniu.book.javaConcurrencyInPractice.chapter7;


import com.geniu.book.javaConcurrencyInPractice.chapter6.Ad;

import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

/**
 * @Author: zhongshibo
 * @Date: 2021/1/20 22:50
 */
public class FetchAdTask implements Callable<Ad> {

    @Override
    public Ad call() throws Exception {
        System.out.println("fetch task");
        sleep(1000L);
        return null;
    }
}
