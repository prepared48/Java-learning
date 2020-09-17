package com.geniu.concurrent.future;

import java.util.concurrent.Callable;

/**
 * callable 能获取返回
 *
 * @Author: zhongshibo
 * @Date: 2020/9/17 09:07
 */
public class TestCallable {

	public static void main(String[] args) {
		Callable<String> callable = () -> {
			// Perform some computation
			Thread.sleep(2000);
			return "Return some result";
		};
	}
}
