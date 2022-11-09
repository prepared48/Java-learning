package com.geniu.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhongshibo
 * @Date: 2020/11/4 15:05
 */
public class TestTernaryOperator {

	public static void main(String[] args) {
		Map<String, BooleanTest> map = new HashMap<String, BooleanTest>();
		System.out.println(map != null);
//		BooleanTest b = (map != null ? map.get("test") : false);
	}
}
