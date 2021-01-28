package com.geniu.base.basetype;

/**
 * Long 类型的比较
 * 如果Long的值在[-127,128]之间，用“==”判断是否相等是没问题的，如果不在这个区间，是不能用“==”的。
 *
 * @Author: zhongshibo
 * @Date: 2020/9/17 19:44
 */
public class TestLong {

	public static void main(String[] args) {
//		test2Error();
//		test2Right();
//		test2Right2();
		testlong();
	}

	public static void testDecimal() {
		Long l1 = 23L;
		Long l2 = 23L;
		System.out.println(l1 == l2);
	}

	public static void test1() {
		Long l1 = 23L;
		Long l2 = 23L;
		System.out.println(l1 == l2);
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
