package com.geniu.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试打印异常
 *
 * @Author: zhongshibo
 * @Date: 2021/1/20 11:35
 */
public class TestLogException {

    private static Logger log = LoggerFactory.getLogger(TestLogException.class);

    public static void main(String[] args) {

        try {
            System.out.println(10 / 0);
        } catch (Exception e) {
            //log.error("出错喽{}", e.getMessage());
            log.error("出错喽{}", e);
        }
    }
}
