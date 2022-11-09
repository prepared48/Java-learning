package com.geniu.base.extend;

/**
 * @Author: zhongshibo
 * @Date: 2022/9/9 20:25
 */
public abstract class AbsAdaptorService {

	private TestService testService = new TestService();

	protected <T> Adaptor<T> adaptor(TestService testService) {
		System.out.println("init.abs");
		return new Adaptor<>(testService);
	}
}
