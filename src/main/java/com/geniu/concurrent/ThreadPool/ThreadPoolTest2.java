package com.geniu.concurrent.ThreadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程池测试
 * 使用丢弃策略处理多余线程，不管多余线程
 */
public class ThreadPoolTest2 {

	public static void main(String[] args) {

		LinkedBlockingQueue<Runnable> queue =
				new LinkedBlockingQueue<Runnable>(5);
		// 丢弃策略，多余的线程直接不处理，丢弃
		RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
		// 创建线程池 5 10 60s
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60,
				TimeUnit.SECONDS, queue, handler);

		for (int i = 1; i <= 16; i++) {
			threadPool.execute(
					new Thread(new ThreadTest(), "Thread".concat(i + "")));

			System.out.println("线程池中活跃的线程数： " + threadPool.getPoolSize());

			if (queue.size() > 0) {
				System.out.println("----------------队列中阻塞的线程数" + queue.size());
			}
		}
		threadPool.shutdown();
	}

}
