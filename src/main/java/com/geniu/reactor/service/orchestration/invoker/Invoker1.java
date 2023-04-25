package com.geniu.reactor.service.orchestration.invoker;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @Author: zhongshibo
 * @Date: 2023/4/25 15:31
 */
public class Invoker1 {

	public static Mono<String> invoke1() {
		return Mono.
				fromSupplier(() -> {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					return "invoker1";
				})
				.subscribeOn(Schedulers.parallel())
				.doOnError(e -> {
					System.out.println("error invoker1");
				});
	}
}
