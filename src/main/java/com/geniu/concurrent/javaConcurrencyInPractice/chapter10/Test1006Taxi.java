package com.geniu.concurrent.javaConcurrencyInPractice.chapter10;

import com.geniu.concurrent.javaConcurrencyInPractice.chapter4.Point;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * 通过公开调用来避免在相互协作的对象之间产生死锁
 *
 * @Author: zhongshibo
 * @Date: 2021/2/25 10:36
 */
@ThreadSafe
public class Test1006Taxi {

    @GuardedBy("this")
    private Point location, destination;

    private final Test1006Dispatcher dispatcher;

    public Test1006Taxi(Point location, Point destination, Test1006Dispatcher dispatcher) {
        this.location = location;
        this.destination = destination;
        this.dispatcher = dispatcher;
    }

    public synchronized Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        boolean reachedDestination;
        // 并发场景下，修改location
        synchronized (this) {
            this.location = location;
            reachedDestination = location.equals(destination);
        }
        if (reachedDestination) {
            // notifyAvailable 是线程安全的
            dispatcher.notifyAvailable(this);
        }
    }
}
