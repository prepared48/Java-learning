package com.geniu.sources.Aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 代理类替代被代理类，实现被代理类的方法，此外，代理类还会增加一些其他操作。
 * 这里是：代理类实现被代理类Student提交作业，此外增加打印信息（额外功能）
 * 代理测试demo
 */
public class ProxyTest {

	public static void main(String[] args) {
		//创建一个实例对象，这个对象是被代理的对象
		IPerson linqian = new Student("林浅");
		//创建一个与代理对象相关联的InvocationHandler
		InvocationHandler stuHandler = new StuInvocationHandler<IPerson>(linqian);
		//创建一个代理对象stuProxy来代理linqian，代理对象的每个执行方法都会替换执行Invocation中的invoke方法
		IPerson stuProxy = (IPerson) Proxy.newProxyInstance(IPerson.class.getClassLoader(), new Class<?>[]{IPerson.class}, stuHandler);
		//代理执行交作业的方法
		stuProxy.giveTask();
	}
}