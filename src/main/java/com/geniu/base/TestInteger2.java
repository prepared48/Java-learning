package com.geniu.base;

/**
 * 测试 自动拆装箱与缓存
 * <p>
 * 我们普遍认为下面两个结果都是 false，但是实际结果是第一个是true，第二个是false
 * 因为范围在-128 至 127 之间的时候，会直接使用一个对象，而不是创建一个对象
 *
 * @Author: zhongshibo
 * @Date: 2020/10/29 09:22
 */
public class TestInteger2 {

	public static void main(String... strings) {

		Integer integer1 = 3;
		Integer integer2 = 3;

		if (integer1 == integer2)
			System.out.println("integer1 == integer2");
		else
			System.out.println("integer1 != integer2");

		Integer integer3 = 300;
		Integer integer4 = 300;

		if (integer3 == integer4)
			System.out.println("integer3 == integer4");
		else
			System.out.println("integer3 != integer4");
	}

}
