package com.geniu.concurrent.javaConcurrencyInPractice.chapter2;

/**
 * 说明竞态条件
 * 延迟初始化中的竞态条件
 *
 * @Author: zhongshibo
 * @Date: 2021/2/22 16:10
 */
public class Test0203LazyInitRace {

    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        // 先检查后执行，这种场景下会发生竞态条件
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }
}
