package com.geniu.base.collection.set;

import com.google.common.collect.Sets;
import io.vavr.collection.Stream;

import java.util.Iterator;
import java.util.Set;

/**
 * 测试set的遍历
 *
 * @Author: zhongshibo
 * @Date: 2021/6/18 11:05
 */
public class TestSetIterator {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

    /**
     * set 自带的方法iterator()
     */
    public static void test1() {
        Set<String> names = Sets.newHashSet("Tom", "Jane", "Karen");
        Iterator<String> namesIterator = names.iterator();
        while (namesIterator.hasNext()) {
            System.out.println(namesIterator.next());
        }
    }

    /**
     * 使用Iterator的forEachRemaining方法
     */
    public static void test2() {
        Set<String> names = Sets.newHashSet("Tom", "Jane", "Karen");
        Iterator<String> namesIterator = names.iterator();
        namesIterator.forEachRemaining(System.out::println);
    }

    /**
     * 使用for循环遍历
     */
    public static void test3() {
        Set<String> names = Sets.newHashSet("Tom", "Jane", "Karen");
        for (String name : names) {
            System.out.println(name);
        }
    }

    /**
     * 转换成Array，然后使用下标访问元素
     * 缺点：相当于遍历了两次，因为toArray遍历了一次
     */
    public static void test4() {
        Set<String> names = Sets.newHashSet("Tom", "Jane", "Karen");
        Object[] namesArray = names.toArray();
        for (int i = 0; i < namesArray.length; i++) {
            System.out.println(namesArray[i]);
        }
    }

    /**
     * 创建索引，然后zip压缩set
     * 使用过 vavr 工具包
     */
    public static void test5() {
        Set<String> names = Sets.newHashSet("Tom", "Jane", "Karen");
        Stream.ofAll(names)
                .zipWithIndex()
                .forEach(t -> System.out.println(t._2() + ": " + t._1()));
    }
}
