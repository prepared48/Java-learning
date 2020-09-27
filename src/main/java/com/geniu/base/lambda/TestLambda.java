package com.geniu.base.lambda;

/**
 * 测试lambda表达式
 *
 * @Author: zhongshibo
 * @Date: 2020/9/24 10:41
 */
public class TestLambda {

	public static void main(String[] args) {
		//	输入一个数，与100比较大小
		//	实现方式1 匿名内部类：
//		Comparable<Integer> comparable1 = new Comparable<Integer>() {
//			@Override
//			public int compareTo(Integer o) {
//				return Integer.compare(o, 100);
//			}
//		};
//		comparable1.compareTo(2);

		//	实现方式2 Lambda表达式：实现只有一个抽象方法的Comparable接口时会自行匹配到compareTo方法，在箭头左侧编写对应参数个数的参数名，箭头右侧编写方法的实现代码
		Comparable<Integer> comparable2 = (x) -> {
			x = 110;
			System.out.println("x = " + x);
			return Integer.compare(x, 100);
		};
		comparable2.compareTo(2);
	}
}
