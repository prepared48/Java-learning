package com.geniu.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhongshibo
 * @Date: 2020/11/4 15:05
 */
public class TestTernaryOperator {

	public static void main(String[] args) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		System.out.println(map != null);
		Boolean b = (map != null ? map.get("test") : false);
	}
}
