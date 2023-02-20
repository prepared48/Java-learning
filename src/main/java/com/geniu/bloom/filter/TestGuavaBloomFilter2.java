package com.geniu.bloom.filter;

import com.geniu.utils.JsonUtil;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * @Author: zhongshibo
 * @Date: 2023/2/2 20:45
 */
public class TestGuavaBloomFilter2 {

	public static void main(String[] args) {
		test();
	}
	private static Map<String, BloomFilter<String>> bizNameBloomFilter = new HashMap<>();

	public static void test() {
		System.out.println("start map= " + JsonUtil.toJson(bizNameBloomFilter));
		// 每个 bizName 一个过滤器
		String bizName = "quanqiutong_1";
		if (Objects.isNull(bizNameBloomFilter.get(bizName))) {
			bizNameBloomFilter.put(bizName, BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 100000, 0.01));
		}
		BloomFilter<String> stringBloomFilter = bizNameBloomFilter.get(bizName);
		Random random = new Random(10000);

		for(int i = 0; i < 10000; i++) {
//			System.out.println(random.nextInt() + "_" + random.nextInt());
			stringBloomFilter.put(random.nextInt() + "_" + random.nextInt());
		}
		System.out.println(stringBloomFilter.mightContain("8c05d0b790c6896c"));
		System.out.println(stringBloomFilter.mightContain("8c05d0b790c6896c2"));

		System.out.println("bloomFilter=" + JsonUtil.toJson(stringBloomFilter.toString()));

		System.out.println("end map= " + JsonUtil.toJson(bizNameBloomFilter));
	}
}
