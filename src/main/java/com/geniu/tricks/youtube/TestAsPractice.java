package com.geniu.tricks.youtube;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 小技能：Pattern.asPredicate()
 *
 * @Author: zhongshibo
 * @Date: 2021/5/13 09:38
 */
public class TestAsPractice {

    public static void main(String[] args) {
        test2();
    }

    /**
     * 单条件filter
     */
    public static void test1() {
        List names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "Hell", "opt");
        Predicate<String> fourLetterLong1 = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 4 ? true : false;
            }
        };
        names.stream()
                .filter(fourLetterLong1)
                .forEach((n) -> System.out.println("this is:" + n));
    }

    /**
     * and 多条件 filter，使用 Predicate
     */
    public static void test2() {
        List names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Hask000", "Lisp", "Hell", "opt");

        Predicate<String> fourLetterLong1 = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 4 ? true : false;
            }
        };

        Predicate<String> startsWith = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.startsWith("Hask") ? true : false;
            }
        };

        names.stream()
                .filter(fourLetterLong1.and(startsWith))
                .forEach((n) -> System.out.println("this is:" + n));
    }

    /**
     * or 条件 filter，逻辑或
     */
    public static void test3() {
        List names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "Hell", "opt");

        Predicate<String> fourLetterLong1 = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 4 ? true : false;
            }
        };

        Predicate<String> startsWith = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.equals("opt") ? true : false;
            }
        };

        names.stream()
                .filter(fourLetterLong1.or(startsWith))
                .forEach((n) -> System.out.println("this is:" + n));

    }

    /**
     * 逻辑非 negate 方法
     */
    public static void test4() {
        List names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "Hell", "opt");

        Predicate<String> fourLetterLong1 = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 4 ? true : false;
            }
        };

        names.stream()
                .filter(fourLetterLong1.negate())
                .forEach((n) -> System.out.println("this is:" + n));
    }
}
