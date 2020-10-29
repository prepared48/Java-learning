package com.geniu.base;

/**
 * 测试自动装箱
 * <p>
 * ps: 这其实是三目运算符的语法规范。当第二，第三位操作数分别为基本类型和对象时，其中的对象就会拆箱为基本类型进行操作。
 * 所以：如果 i 是 null 的时候，运行三目运算符会报空指针异常
 *
 * @Author: zhongshibo
 * @Date: 2020/10/29 09:12
 */
public class TestPack2 {

	public static void main(String[] args) {
		boolean flag = true;
		Integer i = null;
		int j = 1;
		int k = flag ? i : j;
		System.out.println(k);
	}


}
