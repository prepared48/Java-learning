package com.geniu.book.deepinJVM.chapter3;

/**
 * VM参数：
 * -verbose:gc
 * -Xms20M
 * -Xmx20M
 * -Xmn10M
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=1 ## 年龄超过1岁之后，对象会进入老年代
 * -XX:+PrintTenuringDistribution
 *
 * @Author: zhongshibo
 * @Date: 2021/3/31 21:34
 */
public class TestOldObject {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }
}
