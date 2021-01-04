package com.geniu.concurrent.cas;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 使用CAS解决并发问题
 *
 * @Author: zhongshibo
 * @Date: 2021/1/4 17:24
 */
public class TestCas {

    AtomicLong count = new AtomicLong(0);

    public static void main(String[] args) {

    }


    void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count.getAndIncrement();
        }
    }
}
