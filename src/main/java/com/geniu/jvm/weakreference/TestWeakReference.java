package com.geniu.jvm.weakreference;

import java.lang.ref.WeakReference;

/**
 * WeakReference 持有的对象，也不是有GC就回收掉
 *
 * @Author: zhongshibo
 * @Date: 2021/4/22 17:04
 */
public class TestWeakReference {

    public static void main(String[] args) {
        Student s = new Student("张三", 11);
        WeakReference<Student> reference = new WeakReference<Student>(s);
        System.gc();
        System.out.println(reference.get());//返回不是null
        s = null;
        System.gc();
        System.out.println(reference.get());//返回null
    }
}
