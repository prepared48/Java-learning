package com.geniu.base.collection;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * 按照插入顺序有序的HashMap
 *
 * @author prepared
 */
public class OrderHashMap {

    public static void main(String[] args) {
        LinkedHashMap<String, String> test = new LinkedHashMap();
        test.put("b", "bb");
        test.put("a", "aa");
        test.put("d", "dd");
        test.put("zz", "zzz");
        test.put("cc", "ccc");
        System.out.println(test.keySet());
        System.out.println(test.values());

//        Iterator<Map.Entry<String, String>> iterator = test.entrySet().iterator();
//
//        while (iterator.hasNext()) {
//            Map.Entry<String, String> next = iterator.next();
//            System.out.println(next.getKey() + ": " + next.getValue());
//        }

    }
}
