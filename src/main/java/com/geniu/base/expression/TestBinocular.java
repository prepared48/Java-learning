package com.geniu.base.expression;

/**
 * 测试三目表达式
 * 运算符的优先级：+ > 三目运算符
 * 所以如果以下三目运算符的目标是 计算 (a+b)和（c+d）
 * 应该加括号
 * （c==null?0L:c） + d
 * 如果不加括号，相当于：c==null?0L:（c + d）
 *
 * @Author: zhongshibo
 * @Date: 2021/1/18 14:26
 */
public class TestBinocular {

    public static void main(String[] args) {
        Long a = 1L;
        Long b = 163L;
        System.out.println(a == null ? 0L : a + b);

        Long c = null;
        Long d = 163L;
        System.out.println(c == null ? 0L : c + d);

    }
}
