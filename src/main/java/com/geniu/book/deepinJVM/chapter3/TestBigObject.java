package com.geniu.book.deepinJVM.chapter3;

/**
 * VM参数：
 * -verbose:gc
 * -XX:+UseParNewGC ## 使用ParNew+Serial Old的收集器组合进行内存回收
 * -Xms20M
 * -Xmx20M
 * -Xmn10M
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 * -XX:PretenureSizeThreshold=3145728 ## 这个参数只对Serial和ParNew两款收集器有效
 * <p>
 * 发现，老年代使用率为40%，刚好4M，大对象直接分配到老年代
 * 》the space 10240K,  40% used
 *
 * @Author: zhongshibo
 * @Date: 2021/3/31 21:24
 */
public class TestBigObject {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }
}
