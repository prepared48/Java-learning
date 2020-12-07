package com.geniu.concurrent.ThreadPool;

import java.util.Date;
import java.util.concurrent.*;


/**
 * 线程池测试
 * 使用丢弃策略处理多余线程，不管多余线程
 */
public class ThreadPoolTest2 {
	public static void main(String[] args) {
		Long a = 625L;
		Long c = 1250L;
		Long b = 1875L;
		Long d = 2500L;
		System.out.println(a + b + c + d);

//		ThreadPoolTest2 test2 = new ThreadPoolTest2();
//		test2.testCalculate();
//		Long result1 = test2.ConcurrentCalculateBigNum();
//		Long result2 = test2.calculateBigNum(0L, 1000000000L);
//		System.out.println(result1);
//		System.out.println(result2);
	}

	public void testThreadpool() {
		LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(5);
		// 丢弃策略，多余的线程直接不处理，丢弃
		RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
		// 创建线程池 5 10 60s
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60,
				TimeUnit.SECONDS, queue, handler);
		for (int i = 1; i <= 16; i++) {
			threadPool.execute(new Thread(new ThreadTest(), "Thread".concat(i + "")));
			System.out.println("线程池中活跃的线程数： " + threadPool.getPoolSize());
			if (queue.size() > 0) {
				System.out.println("-------队列中阻塞的线程数" + queue.size());
			}
		}
		threadPool.shutdown();
	}

	public Long testCalculate() {
		LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(5);
		// 丢弃策略，多余的线程直接不处理，丢弃
		RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, 10, 60,
				TimeUnit.SECONDS, queue, handler);
		Thread1 thread1 = new Thread1();
		FutureTask task1 = new FutureTask(thread1);
		threadPool.execute(task1);
		Long sum = null;
		try {
			sum = (Long) task1.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("结果：" + sum);
		return sum;
	}


	public Long ConcurrentCalculateBigNum() {
		System.out.println("多线程执行");
		LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(5);
		// 丢弃策略，多余的线程直接不处理，丢弃
		RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, 10, 60,
				TimeUnit.SECONDS, queue, handler);
		Date startTime = new Date();
		System.out.println("多线程 startTime: " + startTime);
		Thread1 thread1 = new Thread1();
		Thread2 thread2 = new Thread2();
		Thread3 thread3 = new Thread3();
		Thread4 thread4 = new Thread4();
		FutureTask task1 = new FutureTask(thread1);
		FutureTask task2 = new FutureTask(thread2);
		FutureTask task3 = new FutureTask(thread3);
		FutureTask task4 = new FutureTask(thread4);
		threadPool.execute(task1);
		threadPool.execute(task2);
		threadPool.execute(task3);
		threadPool.execute(task4);
		Long sum = 0L;
		try {
			sum = (Long) task1.get() + (Long) task2.get() + (Long) task3.get() + (Long) task4.get();
			System.out.println("结果1：" + (Long) task1.get());
			System.out.println("结果2：" + (Long) task2.get());
			System.out.println("结果3：" + (Long) task3.get());
			System.out.println("结果4：" + (Long) task4.get());
			System.out.println("sum：" + sum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		Date endTime = new Date();
		System.out.println("多线程 endTime: " + endTime);
		System.out.println("多线程耗时：" + (endTime.getTime() - startTime.getTime()));
		return sum;
	}

	public Long calculateBigNum(Long startNum, Long num) {
		Date startTime = new Date();
		System.out.println("单次 startTime: " + startTime);
		Long sum = 0L;
		for (Long i = startNum; i < num; i++) {
			sum += num;
		}
		Date endTime = new Date();
		System.out.println("单次 endTime: " + endTime);
		System.out.println("单次 耗时：" + (endTime.getTime() - startTime.getTime()));
		return sum;
	}

	private class Thread1 implements Callable {

		@Override
		public Long call() {
			return calculateBigNum(0L, 250000000L);
		}
	}

	private class Thread2 implements Callable {

		@Override
		public Long call() {
			return calculateBigNum(250000000L, 500000000L);
		}
	}

	private class Thread3 implements Callable {

		@Override
		public Long call() {
			return calculateBigNum(500000000L, 750000000L);
		}
	}

	private class Thread4 implements Callable {

		@Override
		public Long call() {
			return calculateBigNum(750000000L, 1000000000L);
		}
	}

}
