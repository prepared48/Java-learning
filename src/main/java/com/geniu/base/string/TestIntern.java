package com.geniu.base.string;

/**
 * 测试intern
 * inter的功能：在每次赋值的时候使用 String 的 intern 方法，如果常量池中有相同值，就会重复使用该对象，返回对象引用。
 *
 * @Author: zhongshibo
 * @Date: 2020/11/3 14:02
 */
public class TestIntern {

	public static void main(String[] args) {
		String s = "abc".intern();
	}
}
