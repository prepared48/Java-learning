package com.geniu.book.javaConcurrencyInPractice.chapter12;

import org.omg.CORBA.Object;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.Semaphore;

/**
 * 基于信号量的有界缓存
 * 实际情况中，如果需要有界缓存，应该直接使用 ArrayBlockingQueue 或者 LinkedBlockingQueue
 *
 * @Author: zhongshibo
 * @Date: 2021/2/27 21:56
 */
@ThreadSafe
public class Test1201BoundedBuffer<E> {

    // availableItems可以从缓存中删除的元素个数, availableSpaces可以插入到缓存中的元素个数
    private final Semaphore availableItems, availableSpaces;

    @GuardedBy("this")
    private final E[] items;

    @GuardedBy("this")
    private int putPosition = 0, takePosition = 0;

    public Test1201BoundedBuffer(int capacity) {
        availableItems = new Semaphore(0);
        availableSpaces = new Semaphore(capacity);
        // 问题：不能创建 E 对象数组，只能创建Object数组
        items = (E[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return availableItems.availablePermits() == 0;
    }

    public boolean isFull() {
        return availableSpaces.availablePermits() == 0;
    }

    public void put(E x) throws InterruptedException {
        // 空间减1
        availableSpaces.acquire();
        doInsert(x);
        // 数据允许+1
        availableItems.release();
    }

    public E take() throws InterruptedException {
        availableItems.acquire();
        E item = doExtract();
        availableSpaces.release();
        return item;
    }

    private synchronized E doExtract() {
        int i = takePosition;
        E x = items[i];
        items[i] = null;
        takePosition = (++i == items.length) ? 0 : i;
        return x;
    }

    private synchronized void doInsert(E x) {
        int i = putPosition;
        items[i] = x;
        putPosition = (++i == items.length) ? 0 : i;
    }
}
