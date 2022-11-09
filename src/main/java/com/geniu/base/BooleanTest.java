package com.geniu.base;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: zhongshibo
 * @Date: 2022/9/28 14:22
 */
public class BooleanTest {

	public static void main(String[] args) {
		boolean flag = true;
		testBoolean1(flag);
		System.out.println(flag);

		Boolean flag2 = true;
		testBoolean2(flag2);
		System.out.println(flag2);

		BooleanVO booleanVO = new BooleanVO();
		testBoolean3(booleanVO);
		System.out.println(booleanVO);
	}

	public static void testBoolean1(boolean flag) {
		flag = false;
	}

	public static void testBoolean2(Boolean flag) {
		flag = false;
	}

	public static void testBoolean3(BooleanVO booleanVO) {
		booleanVO.setFlag(false);
	}

	@Data
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class BooleanVO {
		private boolean flag;
	}
}
