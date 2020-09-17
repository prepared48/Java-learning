package com.geniu.sources.Aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 类中持有一个被代理对象的实例target。
 * InvocationHandler中有一个invoke方法，所有执行代理对象的方法都会被替换成执行invoke方法
 */
public class StuInvocationHandler<T> implements InvocationHandler {
	//invocationHandler持有的被代理对象
	T target;

	public StuInvocationHandler(T target) {
		this.target = target;
	}

	/**
	 * proxy:代表动态代理对象
	 * method：代表正在执行的方法
	 * args：代表调用目标方法时传入的实参
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("代理执行" + method.getName() + "方法");
		Object result = method.invoke(target, args);
		return result;
	}
}