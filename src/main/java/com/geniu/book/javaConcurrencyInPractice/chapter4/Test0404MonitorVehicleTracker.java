package com.geniu.book.javaConcurrencyInPractice.chapter4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于监视器模式的车辆追踪
 * 什么是监视器模式java 的监视器模式，将对象所有可变状态都封装起来，并由对象自己的内置锁来保护, 即是一种实例封闭。
 *
 * @Author: zhongshibo
 * @Date: 2021/2/19 21:44
 */
public class Test0404MonitorVehicleTracker {

    private final Map<String, MutablePoint> locations;

    public Test0404MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    /**
     * 深度拷贝，使用新的内存地址存放相应的数据，不会被影响
     *
     * @param locations
     * @return
     */
    private Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> locations) {
        Map<String, MutablePoint> result = new HashMap<>();
        for (String id : locations.keySet()) {
            result.put(id, locations.get(id));
        }
        // 返回只读的map数据
        return Collections.unmodifiableMap(result);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocations(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if (loc == null) {
            throw new IllegalArgumentException("No such id: " + id);
        }
        loc.x = x;
        loc.y = y;
    }
}
