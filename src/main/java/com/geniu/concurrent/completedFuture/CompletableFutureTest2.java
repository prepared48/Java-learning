package com.geniu.concurrent.completedFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: zhongshibo
 * @Date: 2023/1/31 11:41
 */
public class CompletableFutureTest2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Future<String> stringFuture = calculateAsync();
		System.out.println("result = " + stringFuture.get());

		long start = System.currentTimeMillis();
		CompletableFuture<String> hello_word = CompletableFuture.completedFuture("hello word");
		System.out.println("result = " + hello_word.get() + ", 耗时：" + (System.currentTimeMillis() - start));

	}

	public static Future<String> calculateAsync() throws InterruptedException {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();

		Executors.newCachedThreadPool().submit(() -> {
			Thread.sleep(500);
			completableFuture.complete("Hello");
			return null;
		});

		return completableFuture;
	}
}
