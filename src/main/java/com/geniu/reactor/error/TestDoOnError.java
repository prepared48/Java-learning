package com.geniu.reactor.error;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.LocalTime;

/**
 * 默认所有操作在主线程中执行，如果耗时操作是阻塞性操作，则会阻塞主线程；导致 timeout 会报错，但是不会返回，失效；
 * 增加 .subscribeOn(Schedulers.parallel()) 让接口请求等操作在子线程执行，从而让 timeout 生效。
 *
 * @Author: zhongshibo
 * @Date: 2023/2/20 15:48
 */
public class TestDoOnError {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println("start " + LocalTime.now());
		Mono<String> stringMono = Mono.fromSupplier(() -> {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					return "success";
				})
				.subscribeOn(Schedulers.parallel())
				.timeout(Duration.ofSeconds(1))
				.doOnError(e -> {
					System.out.println(LocalTime.now() + ", " + e.getMessage());
				})
				.onErrorReturn("error")
				.doFinally(e-> {
					System.out.println("finally " + LocalTime.now());
				});

		System.out.println(stringMono.block() + ", 耗时：" + (System.currentTimeMillis() - start));

	}
}
