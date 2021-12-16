//package com.geniu.book.javaConcurrencyInPractice.chapter4;
//
//import apple.laf.JRSUIConstants;
//
//import javax.annotation.concurrent.GuardedBy;
//
///**
// * 通过一个私有锁来保护状态
// *
// * @Author: zhongshibo
// * @Date: 2021/2/19 21:30
// */
//public class Test0403PrivateLock {
//
//    private final Object myLock = new Object();
//
//    @GuardedBy("myLock")
//    JRSUIConstants.Widget widget;
//
//    void someMethod() {
//        synchronized (myLock) {
//            // 访问或者修改widget状态
//        }
//    }
//}
