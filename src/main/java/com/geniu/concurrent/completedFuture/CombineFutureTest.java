package com.geniu.concurrent.completedFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: zhongshibo
 * @Date: 2023/1/31 14:12
 */
public class CombineFutureTest {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		thenCompose();
		thenCombineSupplyAsync();
		thenAcceptBoth();
	}

	/**
	 * 组合多个 CompletableFuture 进行计算
	 */
	public static void thenCompose() throws ExecutionException, InterruptedException {
		CompletableFuture<String> thenCompose = CompletableFuture.supplyAsync(() -> "Hello ")
				.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "Word"));

		System.out.println(thenCompose.get());
	}

	/**
	 * thenCompose + thenApply
	 *
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void thenCombineSupplyAsync() throws ExecutionException, InterruptedException {
		CompletableFuture<String> thenCompose = CompletableFuture.supplyAsync(() -> "Hello ")
				.thenCombine(CompletableFuture.supplyAsync(() -> "Word"), (s1, s2) -> s1 + s2);

		System.out.println(thenCompose.get());
	}

	/**
	 * thenAcceptBoth 组合多个Future，但不需要任何返回值
	 *
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void thenAcceptBoth() throws ExecutionException, InterruptedException {
		CompletableFuture thenCompose = CompletableFuture.supplyAsync(() -> "Hello ")
				.thenAcceptBoth(CompletableFuture.supplyAsync(() -> "Word"),
						(s1, s2) -> System.out.println(s1 + s2));

		System.out.println(thenCompose.get());
	}
}
