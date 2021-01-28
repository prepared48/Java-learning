package com.geniu.base.string;


import org.apache.commons.lang3.StringUtils;

/**
 * 测试 String s = "a" + 变量
 * <p>
 * 总结：
 * 1、如果不是在循环体中进行字符串拼接的话，直接使用+就好了。
 * 2、如果在并发场景中进行字符串拼接的话，要使用StringBuffer来代替StringBuilder。
 * <p>
 * 结论（时长）：StringBuilder<StringBuffer<concat<+<StringUtils.join
 *
 * @Author: zhongshibo
 * @Date: 2020/11/3 11:27
 */
public class TestStringPlus {

	public static void main(String[] args) {
//		String s = "a" + "b";
		testPlus1();
		testPlus2();
		testPlus3();
		testPlus4();
		testPlus5();
//		输出：
//		+ cost:4395
//		StringBuilder cost:5
//		StringBuffer cost:5
//		concat cost:6
//		StringUtils.join cost:4780
	}

	public static void testPlus1() {
		long t1 = System.currentTimeMillis();
		String s = "";
		//这里是初始字符串定义
		for (int i = 0; i < 50000; i++) {
			//这里是字符串拼接代码
			s += String.valueOf(i);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("+ cost:" + (t2 - t1));
	}

	public static void testPlus2() {
		long t1 = System.currentTimeMillis();
		StringBuilder s = new StringBuilder();
		//这里是初始字符串定义
		for (int i = 0; i < 50000; i++) {
			//这里是字符串拼接代码
			s.append(i);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("StringBuilder cost:" + (t2 - t1));
	}

	public static void testPlus3() {
		long t1 = System.currentTimeMillis();
		StringBuffer s = new StringBuffer();
		//这里是初始字符串定义
		for (int i = 0; i < 50000; i++) {
			//这里是字符串拼接代码
			s.append(i);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("StringBuffer cost:" + (t2 - t1));
	}

	public static void testPlus4() {
		long t1 = System.currentTimeMillis();
		String s = "";
		//这里是初始字符串定义
		for (int i = 0; i < 50000; i++) {
			//这里是字符串拼接代码
			s.concat(Integer.toString(i));
		}
		long t2 = System.currentTimeMillis();
		System.out.println("concat cost:" + (t2 - t1));
	}

	public static void testPlus5() {
		long t1 = System.currentTimeMillis();
		String s = "";
		//这里是初始字符串定义
		for (int i = 0; i < 50000; i++) {
			//这里是字符串拼接代码
			s = StringUtils.join(s, ",", i);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("StringUtils.join cost:" + (t2 - t1));
	}
}
