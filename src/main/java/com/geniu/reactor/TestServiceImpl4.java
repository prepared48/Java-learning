package com.geniu.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: zhongshibo
 * @Date: 2022/8/16 16:13
 */
@Slf4j
public class TestServiceImpl4 implements TestServiceI {
	@Override
	public Mono request() {
		long startTime = System.currentTimeMillis();
		log.info("execute.test.service4");
		return Mono.fromSupplier(() -> {
					try {
						System.out.println("service4.threadName=" + Thread.currentThread().getName());
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}catch (Exception e) {
						log.error("service4.error", e);
					}
					return "";
				})
				//增加subscribeOn
//				.subscribeOn(Schedulers.boundedElastic())
				.map(name -> {
					return new TestUser(name);
				})
				.doOnSuccess(e -> {
					log.info("service4.doOnSuccess.time: {} ms ", (System.currentTimeMillis() - startTime));
				})
				.doFinally(e -> {
					log.info("service4.doFinally.time: {} ms ", (System.currentTimeMillis() - startTime));
				});
	}
}
