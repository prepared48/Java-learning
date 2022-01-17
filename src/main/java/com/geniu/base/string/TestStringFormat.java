package com.geniu.base.string;

import java.math.BigDecimal;

/**
 * @Auther: shibo.zhong
 * @Date: 2021/12/30 11:29
 */
public class TestStringFormat {
    public static void main(String[] args) {
//        String s = String.format("", 10);
//        System.out.println(s);

        System.out.println(BigDecimal.valueOf(Long.valueOf(102)).divide(new BigDecimal(100)));
    }
}
