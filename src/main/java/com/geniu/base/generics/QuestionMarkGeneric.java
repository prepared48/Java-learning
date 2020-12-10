package com.geniu.base.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 无界通配符 ？
 *
 * @Auther: zhongshibo
 * @Date: 2020/12/10 10:49
 */
public class QuestionMarkGeneric {

    static int countLegs(List<? extends Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.countLegs();
        }
        return retVal;
    }

    static int countLegs1(List<Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.countLegs();
        }
        return retVal;
    }

    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        // 不会报错
        countLegs(dogs);
        // 报错
//        countLegs1(dogs);
    }
}
