package com.geniu.bloom.filter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.stream.IntStream;

/**
 * @Author: zhongshibo
 * @Date: 2023/2/2 20:30
 */
public class TestGuavaBloomFilter {

	public static void main(String[] args) {
		guavaBloomFilter();
	}

	public static void guavaBloomFilter() {
		BloomFilter<Integer> filter = BloomFilter.create(
				Funnels.integerFunnel(),
				10000,
				0.01);

		IntStream.range(0, 10000).forEach(filter::put);


		for(int i = 0; i < 10000; i++) {
			System.out.println(filter.mightContain(i));
		}
	}
}
