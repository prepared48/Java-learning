package com.geniu.vavr;

import io.vavr.Tuple;
import io.vavr.Tuple2;

/**
 * 创建 tuple 元组
 *
 * @Author: zhongshibo
 * @Date: 2021/1/28 11:06
 */
public class TestNewTuple {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        String s = java8._1;
        Integer i = java8._2;
        Tuple2<String, Integer> that = java8.map(
                s1 -> s.substring(2) + "vr",
                i1 -> i / 8
        );
        System.out.println(that);

        Tuple2<String, Integer> that2 = java8.map(
                (s2, i2) -> Tuple.of(s.substring(2) + "vr", i / 8)
        );
        System.out.println(that2);

        String that3 = java8.apply(
                (s3, i3) -> s.substring(2) + "vr" + i / 8
        );
        System.out.println(that3);
    }


}
