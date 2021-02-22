package com.geniu.concurrent.javaConcurrencyInPractice.chapter3;

import java.util.HashSet;
import java.util.Set;

/**
 * 发布一个对象
 * <p>
 * 把对象的引用保存到一个公有的静态变量中
 *
 * @Author: zhongshibo
 * @Date: 2021/2/22 20:30
 */
public class Test0305PublishObject {

    public static Set<Secret> knownSecrets;

    public void initialize() {
        knownSecrets = new HashSet<Secret>();
    }
}
