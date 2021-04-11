package com.geniu.book.deepinJVM.chapter2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 本机直接内存溢出-使用unsafe分配本机内存
 *
 * @Author: zhongshibo
 * @Date: 2021/4/10 22:16
 */
public class DirectMemoryOOM {

    public static void main(String[] args) {
        int _1MB = 1024 * 1024;
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(false);
        try {
            Unsafe unsafe = (Unsafe) unsafeField.get(null);
            while (true) {
                unsafe.allocateMemory(_1MB);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
