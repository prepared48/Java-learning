package com.geniu.reactor.service.orchestration.invoker;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @Author: zhongshibo
 * @Date: 2023/4/25 15:31
 */
public class Invoker2 {

	public static Mono<String> invoke2() {
		return Mono.fromSupplier(() -> {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					return "invoker2";
				})
				.subscribeOn(Schedulers.parallel())
				.doOnError(e -> {
					System.out.println("error invoker2");
				});
	}
}
