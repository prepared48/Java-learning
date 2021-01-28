package com.geniu.base.lamdaExpression;

import java.util.Comparator;

/**
 * lamda表达式测试，学习
 *
 * @author prepared
 */
public class LambdaExpressionTest {

	public static void main(String[] args) {
		// java8 以前的写法
		Comparator<String> comparator1 = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		};

		int test1 = comparator1.compare("hello", "world");
		System.out.println("test1 = " + test1);

		// 使用 lambda 表达式写法
		Comparator<String> comparator2 =
				(String o1, String o2) -> {
					return o1.compareTo(o2);
				};

		int test2 = comparator1.compare("hello", "world");
		System.out.println("test2 = " + test2);

		// 使用 lambda 表达式简化写法
		Comparator<String> comparator3 =
				(o1, o2) -> o1.compareTo(o2);

		int test3 = comparator3.compare("hello", "world");
		System.out.println("test3 = " + test3);
	}
}
