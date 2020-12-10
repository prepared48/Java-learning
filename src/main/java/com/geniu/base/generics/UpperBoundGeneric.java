package com.geniu.base.generics;

/**
 * 上界通配符
 *
 * @Auther: zhongshibo
 * @Date: 2020/12/10 11:13
 */
public class UpperBoundGeneric {

    private <K extends Animal, E extends Dog> E test(K arg1, E arg2) {
        E result = arg2;
//        arg2.compareTo(arg1);
        //.....
        return result;
    }

}
