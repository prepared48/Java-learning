package com.geniu.concurrent.javaConcurrencyInPractice.chapter7;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 将异常写入日志的 UncaughtExceptionHandler
 *
 * @Author: zhongshibo
 * @Date: 2021/1/27 22:07
 */
public class Test0725UEHLogger implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "Thread terminated with exception: " + t.getName());
    }
}
