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

		IntStream.range(0, 100_000).forEach(filter::put);


		System.out.println(filter.mightContain(10));
		System.out.println(filter.mightContain(11));
		System.out.println(filter.mightContain(12));
		System.out.println(filter.mightContain(13));
		System.out.println(filter.mightContain(14));
		System.out.println(filter.mightContain(21));
		System.out.println(filter.mightContain(22));
		System.out.println(filter.mightContain(23));
		System.out.println(filter.mightContain(24));
		System.out.println(filter.mightContain(25));
	}
}
