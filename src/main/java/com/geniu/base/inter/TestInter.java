package com.geniu.base.inter;

/**
 * 1、接口中的变量默认是 public static final
 * 2、接口中的方法默认是 public abstract
 * 3、接口可以继承多个接口
 *
 * @Author: zhongshibo
 * @Date: 2020/10/11 21:09
 */
public interface TestInter extends TestInter2, TestInter3 {

	public static final String a = "10";

	public abstract void hello();
}
