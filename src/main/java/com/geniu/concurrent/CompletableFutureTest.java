package com.geniu.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * 1）任务执行完成后再并发执行其他任务，可以使用 thenXxxAsync 这样的方法，并且执行完成之后还得返回结果值，那么我们就可以采用 thenApplyAsync 方法。
 * 2）合并两个线程任务的结果，并做进一步累加和处理，这里我们可以采用 thenCombine 方法。
 * 3）两个线程任务并发执行，谁先执行完成就以谁为准，这里我们可以采用 applyToEither 方法。
 *
 * @Author: zhongshibo
 * @Date: 2023/1/30 20:40
 */
public class CompletableFutureTest {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		for(int i = 0; i < 100; i++) {
			task3();
		}
	}

	/**
	 * 执行任务 TASK-A1，然后并发执行任务 TASK-B1、TASK-C1，再异步执行 TASK-D1；
	 */
	public static void task1() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> taskA1 = CompletableFuture.supplyAsync(() -> 1);
		CompletableFuture<Integer> taskB1 = taskA1.thenApplyAsync(Integer -> 2);
		CompletableFuture<Integer> taskC1 = taskA1.thenApplyAsync(Integer -> 3);

		CompletableFuture<Integer> integerCompletableFuture = taskB1.thenCombine(taskC1, Integer::sum)
				.thenCombine(taskA1, Integer::sum)
				.thenApplyAsync(integer -> integer + 4);
		Integer integer = integerCompletableFuture.get();
		System.out.println(integer);
	}

	public static void task2() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> taskA2 = CompletableFuture.supplyAsync(() -> 1);
		CompletableFuture<Integer> taskB2 = taskA2.thenApplyAsync(Integer -> 2);
		CompletableFuture<Integer> taskC2 = taskA2.thenApplyAsync(Integer -> 3);

		CompletableFuture<Integer> integerCompletableFuture = taskB2.applyToEither(taskC2, Function.identity())
				.thenApplyAsync(integer -> integer + 4);
		Integer integer = integerCompletableFuture.get();
		System.out.println(integer);
	}


	public static void task3() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> taskA1 = CompletableFuture.supplyAsync(() -> 1);
		CompletableFuture<Integer> taskB1 = taskA1.thenApplyAsync(Integer -> 2);
		CompletableFuture<Integer> taskC1 = taskA1.thenApplyAsync(Integer -> 3);

		CompletableFuture<Integer> task1 = taskB1.thenCombine(taskC1, Integer::sum)
				.thenCombine(taskA1, Integer::sum)
				.thenApplyAsync(integer -> integer + 4);
//		Integer task1Res = task1.get();
//		System.out.println(task1Res);

		CompletableFuture<Integer> taskA2 = CompletableFuture.supplyAsync(() -> 1);
		CompletableFuture<Integer> taskB2 = taskA2.thenApplyAsync(Integer -> 2);
		CompletableFuture<Integer> taskC2 = taskA2.thenApplyAsync(Integer -> 3);

		CompletableFuture<Integer> task2 = taskB2.applyToEither(taskC2, Function.identity())
				.thenApplyAsync(integer2 -> integer2 + 4);
//		Integer task2Res = task2.get();
//		System.out.println(task2Res);

		CompletableFuture<Integer> result = task1.thenCombine(task2, Integer::sum);
		try {
			Integer resultInteger = result.get(5, TimeUnit.SECONDS);
			System.out.println("reuslt = " + resultInteger);
		} catch (TimeoutException e) {
			System.out.println(0);
			e.printStackTrace();
		}
	}
}
