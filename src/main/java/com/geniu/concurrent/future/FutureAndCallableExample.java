package com.geniu.concurrent.future;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 测试Future
 */
@Slf4j
public class FutureAndCallableExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		// 使用 Callable ，可以获取返回值
		Callable<String> callable = () -> {
			log.info("进入 Callable 的 call 方法");
			// 模拟子线程任务，在此睡眠 2s，
			// 小细节：由于 call 方法会抛出 Exception，这里不用像使用 Runnable 的run 方法那样 try/catch 了
			Thread.sleep(2000);
			return "Hello from Callable";
		};
		Date startTime = new Date();
		log.info("提交 Callable 到线程池");
		Future<String> future = executorService.submit(callable);
		log.info("主线程继续执行");
		// Future.get() blocks until the result is available
		while (!future.isDone()) {
			log.info("future还没结束。。。");
			log.info("主线程等待获取 Future 结果");
			Thread.sleep(1000);
			double elapsedTimeInSec = (System.nanoTime() - startTime.getTime()) / 1000000000;
			if (elapsedTimeInSec > 1) {
				log.warn("future执行时间过长，取消执行。");
				boolean cancel = future.cancel(true);
				log.info("future 取消结果：" + cancel);
			}
		}
		if (!future.isCancelled()) {
			String result = future.get();
			log.info("主线程获取到 Future 结果: {}", result);
			executorService.shutdown();
		} else {
			log.warn("future 被取消执行。");
		}
	}
}