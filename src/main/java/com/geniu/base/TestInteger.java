package com.geniu.base;

/**
 * Integer 类型的比较
 *
 * @Author: zhongshibo
 * @Date: 2020/9/17 19:44
 */
public class TestInteger {

	public static void main(String[] args) {
		test1();
//		test2Error();
//		test2Right();
//		test2Right2();
//		testlong();
	}

	public static void test1() {
		Integer l1 = 21233;
		Integer l2 = 21233;
		System.out.println(l1.intValue() == l2.intValue());
	}

	/**
	 * 错误示例：测试两个大于128的Long型的比较
	 */
	public static void test2Error() {
		Long l1 = 129L;
		Long l2 = 129L;
		System.out.println(l1 == l2);
	}

	/**
	 * 正确示例：测试两个大于128的Long型的比较
	 */
	public static void test2Right() {
		Long l1 = 129L;
		Long l2 = 129L;
		System.out.println(l1.longValue() == l2.longValue());
	}

	/**
	 * 正确示例2：测试两个大于128的Long型的比较
	 */
	public static void test2Right2() {
		Long l1 = 129L;
		Long l2 = 129L;
		System.out.println(l1.equals(l2));
	}

	public static void testlong() {
		long l1 = 129l;
		long l2 = 129l;
		System.out.println(l1 == l2);
	}
}
