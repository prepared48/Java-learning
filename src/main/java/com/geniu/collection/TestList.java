package com.geniu.collection;

import java.util.*;

/**
 * list 可以存放重复数据
 *
 * list转成set
 *
 * @Author: zhongshibo
 * @Date: 2021/1/18 10:03
 */
public class TestList {

    public static void main(String[] args) {
        List<Long> longList = new ArrayList<>();
        longList.add(1L);
        longList.add(1L);
        longList.add(1L);
        longList.add(1L);
//        System.out.println(longList);
        Set<Long> longSet = new HashSet<>(longList);
        Map<Long, List<Long>> map = new HashMap<>();
        List<Long> list = new ArrayList<>();
        list.add(1L);
        map.put(1L,list);
        map.get(1L).add(2L);
        System.out.println(map);
    }
}
