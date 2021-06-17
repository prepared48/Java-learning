package com.geniu.book.javaConcurrencyInPractice.chapter4;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 将线程安全委托给ConcurrentHashMap
 *
 * @Author: zhongshibo
 * @Date: 2021/2/19 22:09
 */
public class Test0407DelegatingVehicleTracker {

    private final ConcurrentHashMap<String, Point> locations;

    private final Map<String, Point> unmodifiableMap;

    public Test0407DelegatingVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }


    public synchronized Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    public synchronized Point getLocation(String id) {
        return locations.get(id);
    }

    public synchronized void setLocations(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        }
    }
}
