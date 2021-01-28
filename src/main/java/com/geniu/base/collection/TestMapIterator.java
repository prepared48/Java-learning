package com.geniu.base.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * map 循环
 *
 * @Author: zhongshibo
 * @Date: 2021/1/18 10:42
 */
public class TestMapIterator {

    public static void main(String[] args) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(1L, 1);
        map.put(2L, 2);
        map.put(3L, 3);
        map.put(4L, 4);
        System.out.println("key循环 ===============");
        // 循环key
        for (Long key : map.keySet()) {
            System.out.println("key=" + key);
        }
        // 循环value
        System.out.println("value循环 ===============");
        for (Integer value : map.values()) {
            System.out.println("value=" + value);
        }
        System.out.println("key, value循环 ===============");
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}
