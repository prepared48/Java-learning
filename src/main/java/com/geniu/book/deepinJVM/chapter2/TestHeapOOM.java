package com.geniu.book.deepinJVM.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Java堆内存溢出异常测试
 * VM参数：-Xms20m -Xmx20m -Xmn10m
 * -XX:+HeapDumpOnOutOfMemoryError # 会打印出现内存溢出异常时，Dump出当前内存堆转存快找以便分析
 * 如何分析 Dumping heap to java_pid73912.hprof 这种 hprof 文件 ? jdk 自带的 jhat 命令
 *
 * @Author: zhongshibo
 * @Date: 2021/4/8 22:15
 */
public class TestHeapOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
