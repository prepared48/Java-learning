package com.geniu.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * list 删除元素，应该使用Iterator的remove方法。
 *
 * @Author: zhongshibo
 * @Date: 2021/1/19 16:49
 */
public class TestListRemove {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        System.out.println("开始添加元素 size:" + list.size());
        for (int i = 0; i < 100; i++) {
            list.add(i + 1);
        }
        System.out.println("元素添加结束 size:" + list.size());
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next % 5 == 0) {
                // 正确写法
//                iterator.remove();
                // 报错写法
                list.remove(next);
            }
        }
        System.out.println("执行结束 size:" + list.size());
    }
}
