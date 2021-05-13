package com.geniu.concurrent.concurrentHashMap;

/**
 * @Author: zhongshibo
 * @Date: 2021/4/28 09:52
 */
public class Test {

    public static void main(String[] args) {
        int n = 16;
        // 右移两位 = 4
        int i = n - (n >>> 2);
        System.out.println(i);
    }
}
