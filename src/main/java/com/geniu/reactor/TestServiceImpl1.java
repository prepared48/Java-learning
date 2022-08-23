package com.geniu.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: zhongshibo
 * @Date: 2022/8/16 16:13
 */
@Slf4j
public class TestServiceImpl1 implements TestServiceI{
	@Override
	public Mono request() {
		log.info("execute.test.service1 ");
		CompletableFuture<String> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
			try {
				System.out.println("service1.threadName=" + Thread.currentThread().getName());
				Thread.sleep(500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return "testname1";
		});

		return Mono.fromFuture(uCompletableFuture).map(name -> {
			return new TestUser(name);
		});
	}
}