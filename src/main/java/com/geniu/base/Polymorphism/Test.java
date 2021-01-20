package com.geniu.base.Polymorphism;

import com.alibaba.fastjson.JSON;


/**
 * @Author: zhongshibo
 * @Date: 2021/1/19 09:26
 */
public class Test {

    public static void main(String[] args) {
        TestInterface testInterface = new TestEntity("zhangsan", 11);
        String s = JSON.toJSONString(testInterface);
        System.out.println(s);
    }
}
