package com.geniu.exception;

/**
 * @Author: zhongshibo
 * @Date: 2020/9/24 10:37
 */
public class TestFinally {

	public static void main(String[] args) {
		System.out.println(testFinally());
	}

	public static int testFinally() {
		int i = 1;
		try {
			i++;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			i++;
		}
		return i;
	}
}
