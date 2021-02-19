package com.geniu.concurrent.javaConcurrencyInPractice.chapter4;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.HashSet;
import java.util.Set;

/**
 * 通过封闭机制来确保线程安全
 *
 * @ThreadSafe 这个注解，只能给代码阅读者一个提醒，表示这个类是线程安全的，但是
 * 实际的线程安全性需要开发者自己保证
 * @Author: zhongshibo
 * @Date: 2021/2/19 21:22
 */
@ThreadSafe
public class Test0402PersonSet {

    @GuardedBy("this")
    private final Set<Person> myset = new HashSet<>();

    public synchronized void addPerson(Person p) {
        myset.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return myset.contains(p);
    }
}
