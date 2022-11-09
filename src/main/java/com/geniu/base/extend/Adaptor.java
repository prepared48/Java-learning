package com.geniu.base.extend;

/**
 * @Author: zhongshibo
 * @Date: 2022/9/9 20:26
 */
public class Adaptor<T> {

	private TestService testService;
	public Adaptor() {

		System.out.println("init.adaptor");
	}

	public Adaptor(TestService testService) {
		System.out.println("init.adaptor");
		this.testService = testService;
	}
}
