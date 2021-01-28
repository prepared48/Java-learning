package com.geniu.base.sources;

import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap {

	public static void main(String[] args) {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap();  //初始化ConcurrentHashMap
		//新增个人信息
		map.put("id", "1");
		map.put("name", "andy");
		map.put("sex", "男");
		//获取姓名
		String name = map.get("name");
		System.out.println(name.equals("andy"));
		//计算大小
		int size = map.size();
		System.out.println(size == 3);

	}

}
