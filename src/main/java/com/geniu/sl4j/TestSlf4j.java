package com.geniu.sl4j;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试Slf4j 日志打印
 * 结论：可以使用大括号作为占位符 {}
 *
 * @Author: zhongshibo
 * @Date: 2020/10/29 11:10
 */
@Slf4j
public class TestSlf4j {

	public static void main(String[] args) {
		testLog();
	}

	public static void testLog() {
		log.info("TestSlf4j 参数{}, 用户{}", "nimei", "zhangsan");
	}
}
