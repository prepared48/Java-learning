package com.geniu.book.javaConcurrencyInPractice.chapter3;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 对数值及其因数分解结果进行缓存的不可变容器类
 *
 * @Author: zhongshibo
 * @Date: 2021/2/22 21:04
 */
@Immutable
public class Test0312OneValueCache {

    private final BigInteger lastNumber;

    private final BigInteger[] lastFactors;

    public Test0312OneValueCache(BigInteger lastNumber, BigInteger[] factors) {
        this.lastNumber = lastNumber;
        this.lastFactors = Arrays.copyOf(factors, factors.length);
    }

    /**
     * 获取数值的因式分解结果
     *
     * @param i
     * @return
     */
    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }

    }
}
