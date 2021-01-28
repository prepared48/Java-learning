package com.geniu.base.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试HashMap初始化的值
 *
 * @Author: zhongshibo
 * @Date: 2020/11/3 17:13
 */
public class TestHashMapInitialSize {

    public static void main(String[] args) {
        int size = 7;
        Map map = new HashMap((int) ((float) size / 0.75 + 1));

    }
}
