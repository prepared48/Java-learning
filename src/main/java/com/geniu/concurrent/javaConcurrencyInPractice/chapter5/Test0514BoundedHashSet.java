package com.geniu.concurrent.javaConcurrencyInPractice.chapter5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用 Semaphore 为容易设置边界
 *
 * @Author: zhongshibo
 * @Date: 2021/2/21 23:17
 */
public class Test0514BoundedHashSet<T> {

    private final Set<T> set;

    private final Semaphore sem;

    public Test0514BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        // 获取一个许可，只有获取到许可才能添加元素
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded) {
                sem.release();
            }
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved) {
            // 删除元素，则释放一个许可
            sem.release();
        }
        return wasRemoved;
    }
}
