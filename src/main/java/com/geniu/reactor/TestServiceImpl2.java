package com.geniu.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * 重要：使用 CompletableFuture 会导致 Mono 的 timeout 失效
 *
 * @Author: zhongshibo
 * @Date: 2022/8/16 16:13
 */
@Slf4j
public class TestServiceImpl2 implements TestServiceI {
	@Override
	public Mono request() {
		log.info("execute.test.service2");
		CompletableFuture<String> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
			try {
				System.out.println("service2.threadName=" + Thread.currentThread().getName());
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return "name2";
		})
				;

		return Mono.fromFuture(uCompletableFuture).map(name -> {
					return new TestUser(name);
				})
				.timeout(Duration.ofSeconds(1))
				;
	}
}
