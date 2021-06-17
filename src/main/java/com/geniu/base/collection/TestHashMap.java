package com.geniu.base.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个HashMap源码问题：https://blog.csdn.net/Prepared/article/details/117947220
 *
 * @Author: zhongshibo
 * @Date: 2021/6/17 17:58
 */
public class TestHashMap {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        System.out.println(map.get(1L));
//        System.out.println(map.get(1));

//        System.out.println(hash(1));
//        System.out.println(hash(1L));
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
