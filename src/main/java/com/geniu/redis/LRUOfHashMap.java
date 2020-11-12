package com.geniu.redis;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用LinkedHashMap实现一个简单的 LRU 算法
 * LinkedHashMap 是撒列表+双向链表的形式实现，用这个数据结构可以实现一个LRU
 *
 * @Author: zhongshibo
 * @Date: 2020/11/12 13:50
 */
public class LRUOfHashMap extends LinkedHashMap {

    private final int CACHE_SIZE;

    /**
     * 设置一个HashMap的初始大小，同时最后一个true指的是让 LinkedHashMap 按照访问顺序来进行排序，最近访问的放在头，最老访问的放在尾
     *
     * @param cacheSize
     */
    public LRUOfHashMap(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return false;
    }

}
