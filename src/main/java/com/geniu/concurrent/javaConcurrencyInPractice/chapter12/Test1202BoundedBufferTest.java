package com.geniu.concurrent.javaConcurrencyInPractice.chapter12;

import junit.framework.TestCase;

/**
 * BoundedBuffer 的基本单元测试
 *
 * @Author: zhongshibo
 * @Date: 2021/2/27 22:15
 */
public class Test1202BoundedBufferTest extends TestCase {

    private static final long LOCKUP_DETECT_TIMEOUT = 10;

    public void testIsEmptyWhenConstructed() {
        Test1201BoundedBuffer<Integer> bb = new Test1201BoundedBuffer<>(10);
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }

    public void testIsFullAfterPuts() throws InterruptedException {
        Test1201BoundedBuffer<Integer> bb = new Test1201BoundedBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            bb.put(i);
        }
        assertTrue(bb.isFull());
        assertFalse(bb.isEmpty());
    }

    /**
     * 测试阻塞行为以及对中断的响应性
     */
    public void testTakeBlocksWhenEmpty() {
        final Test1201BoundedBuffer<Integer> bb = new Test1201BoundedBuffer<>(10);
        Thread taker = new Thread() {
            @Override
            public void run() {
                try {
                    int unused = bb.take();
                    // TestCase 带的方法，表示失败
                    fail();
                } catch (InterruptedException success) {
                    // 一个空队列，获取对象就是应该报异常。成功
                }
            }
        };

        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive());
        } catch (InterruptedException unexpected) {
            fail();
        }
    }
}
