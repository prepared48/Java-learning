package com.geniu.jvm.parentdelegation;

/**
 * @Author: zhongshibo
 * @Date: 2021/3/3 09:10
 */
public class TestMain {

    public static void main(String[] args) {
        System.out.println("this is TestMain, 我是被谁加载的：" + TestMain.class.getClassLoader());
        A a = new A();
        a.sayHi();
    }
}
