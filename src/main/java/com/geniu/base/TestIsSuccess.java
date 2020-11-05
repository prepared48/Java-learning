package com.geniu.base;

/**
 * 阿里巴巴开发手册：禁止使用isSuccess作为变量名。
 * 问题1：序列化、反序列化的时候会出现错误，以为变量名是 success；
 * 问题2：get方法也是 isSuccess，rpc反向解析的时候，会以为对应的属性名字是 success。
 *
 * @Author: zhongshibo
 * @Date: 2020/11/5 10:52
 */
public class TestIsSuccess {

	private boolean isSuccess;
	private boolean Success;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean success) {
		isSuccess = success;
	}


}
