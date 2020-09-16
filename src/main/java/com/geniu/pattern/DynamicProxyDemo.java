package com.geniu.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 测试动态代理demo
 *
 * @Author: prepared
 * @Date: 2020/9/16 10:22
 */
public class DynamicProxyDemo {

	public static void main(String[] args) {
		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println(method);
				if (method.getName().equals("morning")) {
					System.out.println("Good morning, " + args[0]);
				}
				return null;
			}
		};
		Hello hello = (Hello) Proxy.newProxyInstance(
				Hello.class.getClassLoader(), // 传入ClassLoader
				new Class[]{Hello.class}, // 传入要实现的接口
				handler); // 传入处理调用方法的InvocationHandler
		hello.morning("Bob");
	}

	interface Hello {
		void morning(String name);
	}

}
