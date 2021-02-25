package com.geniu.concurrent.javaConcurrencyInPractice.chapter10;


import java.util.HashSet;
import java.util.Set;

/**
 * 通过公开调用来避免在相互协作的对象之间产生死锁
 *
 * @Author: zhongshibo
 * @Date: 2021/2/25 10:37
 */
public class Test1006Dispatcher {

    private final Set<Test1006Taxi> taxis;

    private final Set<Test1006Taxi> availableTaxis;

    public Test1006Dispatcher(Set<Test1006Taxi> taxis, Set<Test1006Taxi> availableTaxis) {
        this.taxis = taxis;
        this.availableTaxis = availableTaxis;
    }

    public synchronized void notifyAvailable(Test1006Taxi test1006Taxi) {
        availableTaxis.add(test1006Taxi);
    }

    /**
     * 只有 taxi 存在资源竞争
     *
     * @return
     */
    public Image getImage() {
        Set<Test1006Taxi> copy;
        synchronized (this) {
            copy = new HashSet<>(taxis);
        }
        Image image = new Image();
        for (Test1006Taxi t : copy) {
            image.drawMarker(t.getLocation());
        }
        return image;
    }
}
