package com.geniu.concurrent.completedFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: zhongshibo
 * @Date: 2023/1/31 11:48
 */
public class ComputationAsyncTest {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		thenRun();
	}

	public static void supplyAsyncTest() throws ExecutionException, InterruptedException {
		CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> "hello word");
		System.out.println(supplyAsync.get());
	}


	/**
	 * thenApply 拿到计算结果之后，再处理，获取最终结果
	 *
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void thenApplyTest() throws ExecutionException, InterruptedException {
		CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> "hello word");

		CompletableFuture<String> thenApply = supplyAsync.thenApply(s -> s + " prepred");

		System.out.println(thenApply.get());
	}

	/**
	 * thenAccept 没有返回值，返回 void
	 *
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void thenAccept() throws ExecutionException, InterruptedException {
		CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> "hello word");

		CompletableFuture<Void> voidCompletableFuture = supplyAsync.thenAccept(s -> System.out.println(s + " prepred"));

		System.out.println(voidCompletableFuture.get());
	}


	/**
	 * thenRun 即不需要计算的中间返回值作为参数，也需要返回值
	 *
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void thenRun() throws ExecutionException, InterruptedException {
		CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> "hello word");

		CompletableFuture<Void> voidCompletableFuture = supplyAsync.thenRun(() -> System.out.println(" finished"));

		System.out.println(voidCompletableFuture.get());
	}
}
