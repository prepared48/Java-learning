package com.geniu.book.deepinJVM.chapter2;

/**
 * 虚拟机栈和本地方法栈OOM测试
 *
 * @Author: zhongshibo
 * @Date: 2021/4/10 21:57
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: " + oom.stackLength);
            throw e;
        }
    }
}
