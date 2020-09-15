package com.geniu.concurrent;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture 测试 串行流程、并行流程
 */
public class TestCompletableFutureDemo {

	public static void main(String[] args) {

		CompletableFuture<String> f0 = CompletableFuture.supplyAsync(
				() -> "Hello World")      //①
				.thenApply(s -> s + " QQ")  //②
				.thenApply(String::toUpperCase);//③

		System.out.println(f0.join());
		//输出结果
		//HELLO WORLD QQ
	}
}
