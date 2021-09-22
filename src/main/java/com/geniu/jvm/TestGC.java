package com.geniu.jvm;

/**
 * -Xms1m -Xmx1m -XX:+UseSerialGC
 *
 *
 * @Author: zhongshibo
 * @Date: 2020/9/15 17:39
 */
public class TestGC {

	public static void main(String[] args) {
		Integer a = 1;
		System.out.println(a == 1 ? "等于" : "不等于");
		Boolean bool = false;
		System.out.println(bool ? "真" : "假");
	}
}
