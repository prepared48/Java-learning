package com.geniu.concurrent.threadlocal;

import java.text.SimpleDateFormat;

/**
 * 注意：这里打印出来的对象是一样的 是toString()的问题
 * simpledateformat对象的toString()，继承自Object的toString，重写了hashcode方法，导致变量的hashcode一致
 * 使用的是patter的hashcode，所以是一样，应为pattern是一样的
 *
 * @Override public int hashCode()
 * {
 * return pattern.hashCode();
 * // just enough fields for a reasonable distribution
 * }
 */
public class TestSafeSimpleDateFormat {

    public static void main(String[] args) throws Exception {
        System.out.println(SafeDateFormat.get());
        System.out.println(Thread.currentThread().getName());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(SafeDateFormat.get());
            }
        }).start();

    }

    static class SafeDateFormat {
        static final ThreadLocal<SimpleDateFormat> sdf =
                ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        static SimpleDateFormat get() {
            return sdf.get();
        }
    }
}