package com.geniu.book.javaConcurrencyInPractice.chapter11;

import javax.annotation.concurrent.ThreadSafe;

/**
 * 在基于散列的Map中使用锁分段技术
 *
 * @Author: zhongshibo
 * @Date: 2021/2/27 21:21
 */
@ThreadSafe
public class Test1108StripedMap {

    private static final int N_LOCKS = 16;

    private final Node[] buckets;

    private final Object[] locks;


    private static class Node {
        Node next;
        Object key;
        Object value;
    }

    public Test1108StripedMap(int numBuckets) {
        buckets = new Node[numBuckets];
        locks = new Object[N_LOCKS];
        for (int i = 0; i < N_LOCKS; i++) {
            locks[i] = new Object();
        }
    }

    private final int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    /**
     * 只需要获取分段锁即可
     *
     * @param key
     * @return
     */
    public Object get(Object key) {
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) {
            for (Node m = buckets[hash]; m != null; m = m.next) {
                if (m.key.equals(key)) {
                    return m.value;
                }
            }
        }
        return null;
    }

    /**
     * 需要获取所有的锁，但并不要求同时获取
     */
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % N_LOCKS]) {
                buckets[i] = null;
            }
        }
    }
}
