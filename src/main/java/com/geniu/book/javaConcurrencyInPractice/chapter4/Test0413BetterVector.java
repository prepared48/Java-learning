package com.geniu.book.javaConcurrencyInPractice.chapter4;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Vector;

/**
 * 扩展Vector并增加一个"若没有则添加"方法
 *
 * @Author: zhongshibo
 * @Date: 2021/2/20 19:21
 */
@ThreadSafe
public class Test0413BetterVector<E> extends Vector<E> {

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (!absent) {
            add(x);
        }
        return absent;
    }
}
