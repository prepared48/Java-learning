package com.geniu.base.boxing;

/**
 * @Auther: shibo.zhong
 * @Date: 2022/1/25 10:30
 */
public class Test {

    public TestI getTest() {
        return new TestExt1();
    }
}
