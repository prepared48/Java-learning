package com.geniu.base;

/**
 * 中信银行笔试题：
 * <p>
 * 结论：byte类型不能直接加减操作
 *
 * @Author: zhongshibo
 * @Date: 2020/10/11 21:04
 */
public class TestByte {

	public static void main(String[] args) {
		byte a = 2, b = 3;
		int c = a + 2;
		int d = a + b;
		// 编译报错
//		byte f = a+2;

//		System.out.println(f);
	}
}
