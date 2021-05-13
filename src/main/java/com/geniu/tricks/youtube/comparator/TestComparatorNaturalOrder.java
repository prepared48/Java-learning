package com.geniu.tricks.youtube.comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Comparator.naturalOrder()
 *
 * @Author: zhongshibo
 * @Date: 2021/5/13 11:34
 */
public class TestComparatorNaturalOrder {

    public static void main(String[] args) {
        List names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "", "Hell", "opt");
        names.sort(Comparator.naturalOrder());
        System.out.println(names);
    }
}
