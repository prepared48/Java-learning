package com.geniu.jvm.parentdelegation;

/**
 * @Author: zhongshibo
 * @Date: 2021/3/3 10:37
 */
public class A {

    public void sayHi() {
        System.out.println("this is A, 我是被谁加载的：" + this.getClass().getClassLoader());
    }
}
