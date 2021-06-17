package com.geniu.book.javaConcurrencyInPractice.chapter4;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 安全发布底层状态的车辆追踪器
 * 将安全性委托给底层的 ConcurrentHashMap
 *
 * @Author: zhongshibo
 * @Date: 2021/2/19 22:45
 */
public class Test0412PublishingVehicleTracker {

    private final Map<String, SafePoint> locations;

    private final Map<String, SafePoint> unmodifiableMap;

    public Test0412PublishingVehicleTracker(Map<String, SafePoint> locations) {
        this.locations = new ConcurrentHashMap<>(locations);
        unmodifiableMap = Collections.unmodifiableMap(this.locations);
    }

    public Map<String, SafePoint> getLocations() {
        return unmodifiableMap;
    }

    public SafePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (!locations.containsKey(id)) {
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        }
        locations.get(id).set(x, y);
    }

}
