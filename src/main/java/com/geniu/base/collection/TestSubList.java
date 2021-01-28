package com.geniu.base.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhongshibo
 * @Date: 2020/11/4 13:57
 */
public class TestSubList {

	public static void main(String[] args) {
		List<String> names = new ArrayList<String>() {{
			add("Hollis");
			add("hollischuang");
			add("H");
		}};
		// 报错  java.util.ArrayList$SubList cannot be cast to java.util.ArrayList
		LinkedList subList = (LinkedList) names.subList(0, 1);
		System.out.println(subList);

		List<Long> ids = new ArrayList<Long>(Arrays.asList(111L, 222L));
	}
}
