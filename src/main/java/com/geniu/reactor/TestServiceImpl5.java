package com.geniu.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * @Author: zhongshibo
 * @Date: 2022/8/16 16:13
 */
@Slf4j
public class TestServiceImpl5 implements TestServiceI {
	@Override
	public Mono request() {
		log.info("execute.test.service5");
		return Mono.fromSupplier(() -> {
					try {
						System.out.println("service5.threadName=" + Thread.currentThread().getName());
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					return "";
				})
				.timeout(Duration.ofSeconds(1))
				.publishOn(Schedulers.boundedElastic())
				.map(name -> {
					return new TestUser(name);
				})
				.onErrorReturn(new TestUser());
	}
}
