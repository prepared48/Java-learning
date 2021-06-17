package com.geniu.book.javaConcurrencyInPractice.chapter5;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 隐藏在字符串链接中的迭代操作（错误示例）
 * toString(), hashCode(), equals(), containsAll(), removeAll(), retainAll() 等方法都会间接调用迭代方法
 * 导致抛出 ConcurrentModificationException
 *
 * @Author: zhongshibo
 * @Date: 2021/2/20 20:19
 */
public class Test0506HiddenIterator {

    private final Set<Integer> set = new HashSet<>();

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer i) {
        set.remove(i);
    }

    public void addTenThings() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            add(r.nextInt());
        }
        // 会调用toString() 方法，会调用迭代方法
        System.out.println("DEBUG: added ten elements to " + set);
    }
}
