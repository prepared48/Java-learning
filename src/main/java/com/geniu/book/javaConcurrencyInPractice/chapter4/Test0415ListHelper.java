package com.geniu.book.javaConcurrencyInPractice.chapter4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 通过客户端枷锁来实现"若没有则添加"
 *
 * @Author: zhongshibo
 * @Date: 2021/2/20 19:27
 */
public class Test0415ListHelper<E> {

    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public boolean putIfAbsent(E x) {
        // 锁住的是list对象，锁住方法没有用
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (!absent) {
                list.add(x);
            }
            return absent;
        }
    }
}
