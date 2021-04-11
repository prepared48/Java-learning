package com.geniu.book.deepinJVM.chapter2;

/**
 * 创建线程导致内存溢出异常
 *
 * @Author: zhongshibo
 * @Date: 2021/4/10 22:02
 */
public class JavaVMStackOOM {

    private void dontStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
