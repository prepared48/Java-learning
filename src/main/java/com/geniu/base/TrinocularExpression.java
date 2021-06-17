package com.geniu.base;

/**
 * @Author: zhongshibo
 * @Date: 2021/6/11 18:01
 */
public class TrinocularExpression {

    public static void main(String[] args) {
//        System.out.println(1 == 2 ? 3 : 4 : 5)
        System.out.println(!true && !true ? (1 == 2 ? 3 : 4) : 5);
    }
}
