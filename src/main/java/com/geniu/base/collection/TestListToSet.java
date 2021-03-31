package com.geniu.base.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhongshibo
 * @Date: 2021/3/29 15:02
 */
public class TestListToSet {

    public static void main(String[] args) {
        List<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        idList.add(1L);
        Set<Long> idSet = new HashSet<>(idList);
        System.out.println(idList.size());
        System.out.println(idSet.size());
        System.out.println(idSet);
        idList.removeAll(idSet);
        System.out.println(idList);
    }
}
