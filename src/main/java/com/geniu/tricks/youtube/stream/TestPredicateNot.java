package com.geniu.tricks.youtube.stream;

import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Predicates.not;

/**
 * lambda 中的 Predicate::not
 *
 * @Author: zhongshibo
 * @Date: 2021/5/13 09:56
 */
public class TestPredicateNot {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        List names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "", "Hell", "opt");

        names.stream()
                .filter(not(String::isEmpty))
                .forEach((n) -> System.out.println("this is:" + n));
        System.out.println("==============");
        System.out.println(names);
    }

}
