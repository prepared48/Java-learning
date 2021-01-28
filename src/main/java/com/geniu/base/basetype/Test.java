package com.geniu.base.basetype;

public class Test {

	public static void main(String[] args) {

		// 2^3 + 2^1 + 2^0
//		System.out.println(3 | 9);
		int a[] = {49, 38, 65, 97, 76, 13, 27, 49};

		a[1] = a[2];
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + ", ");
		}

	}
}
