package com.geniu.base.collection;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 总结1：在使用 java.util.stream.Collectors 类的 toMap()方法转为 Map 集合时，一定要使
 * 用含有参数类型为 BinaryOperator，参数名为 mergeFunction 的方法，否则当出现相同 key
 * 值时会抛出 IllegalStateException 异常。
 * <p>
 * 总结2：在使用 java.util.stream.Collectors 类的 toMap()方法转为 Map 集合时，一定要注
 * 意当 value 为 null 时会抛 NPE 异常。
 *
 * @Author: zhongshibo
 * @Date: 2020/11/5 14:13
 */
public class TestStream {

    public static void main(String[] args) {
//        positive();
//        negative();
        NPE();
    }

    public static void positive() {
        List<Pair<String, Double>> pairArrayList = new ArrayList<>(3);
        pairArrayList.add(new Pair<>("version", 12.10));
        pairArrayList.add(new Pair<>("version", 12.19));
        pairArrayList.add(new Pair<>("version", 6.28));
        Map<String, Double> map = pairArrayList.stream().collect(
                // 生成的 map 集合中只有一个键值对：{version=6.28}
                Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2));
    }

    /**
     * 报错： Duplicate key iERP
     */
    public static void negative() {
        String[] departments = new String[]{"iERP", "iERP", "EIBU"};
        // 抛出 IllegalStateException 异常
        Map<Integer, String> map = Arrays.stream(departments).collect(Collectors.toMap(String::hashCode, str -> str));
    }

    /**
     * 报错：空指针异常
     */
    public static void NPE() {
        List<Pair<String, Double>> pairArrayList = new ArrayList<>(2);
        pairArrayList.add(new Pair<>("version1", 8.3));
        pairArrayList.add(new Pair<>("version2", null));
        Map<String, Double> map = pairArrayList.stream().collect(
// 抛出 NullPointerException 异常
                Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2));
    }
}
