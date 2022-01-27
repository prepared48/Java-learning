package com.geniu.base.boxing;

/**
 * 
 *
 * @Auther: shibo.zhong
 * @Date: 2022/1/25 10:30
 */
public class TestMain {

    public static void main(String[] args) {
        Test test = new Test();
        TestI ext2 = test.getTest();
    }
}
