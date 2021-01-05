package com.geniu.concurrent.stampedLock;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * 使用 StampedLock 一定不要调用中断操作，如果需要支持中断功能，一定使用可中断的悲观读锁 readLockInterruptibly() 和写锁 writeLockInterruptibly()
 * 对于读多写少的场景 StampedLock 性能很好，简单的应用场景基本上可以替代 ReadWriteLock，但是 StampedLock 的功能仅仅是 ReadWriteLock 的子集
 *
 * @Author: zhongshibo
 * @Date: 2021/1/5 11:08
 */
public class TestStampedLock {

    public static void main(String[] args) {
        final StampedLock lock = new StampedLock();
        Thread T1 = new Thread(() -> {
            // 获取写锁
            lock.writeLock();
            // 永远阻塞在此处，不释放写锁
            LockSupport.park();
        });
        T1.start();
        // 保证T1获取写锁
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread T2 = new Thread(() ->
                //阻塞在悲观读锁
                lock.readLock()
        );
        T2.start();
        // 保证T2阻塞在读锁
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //中断线程T2
        //会导致线程T2所在CPU飙升
        T2.interrupt();
        try {
            // 等待T2结束
            T2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
