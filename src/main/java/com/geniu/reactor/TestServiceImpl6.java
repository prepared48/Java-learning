package com.geniu.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @Author: zhongshibo
 * @Date: 2022/8/16 16:13
 */
@Slf4j
public class TestServiceImpl6 implements TestServiceI {
	@Override
	public Mono request() {
		log.info("execute.test.service5");
		return Mono.fromSupplier(() -> {
					try {
						System.out.println("service6.threadName=" + Thread.currentThread().getName());
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					return "name6";
				})
				.publishOn(Schedulers.boundedElastic())
				.map(name -> {
					return new TestUser(name);
				});
	}
}
