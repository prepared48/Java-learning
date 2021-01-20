package com.geniu.base.Polymorphism;

/**
 * @Author: zhongshibo
 * @Date: 2021/1/19 09:26
 */
public class TestEntity implements TestInterface {

    private String name;

    private int age;

    public TestEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
