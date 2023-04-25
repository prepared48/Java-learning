package com.geniu.reactor.flatmap;

import reactor.core.publisher.Mono;

/**
 * @Author: zhongshibo
 * @Date: 2023/4/25 17:17
 */
public class TestFlatMap {

	public static void main(String[] args) {
		Mono.just("input")
				.flatMap(input -> {
					// 执行第一个异步操作
					return Mono.just("result1");
				})
				.flatMap(result1 -> {
					// 执行第二个异步操作，使用第一个异步操作的结果
					return Mono.just("result2" + result1);
				})
				.subscribe(result -> {
					// 处理最终的结果
					System.out.println(result);
				});
	}
}
