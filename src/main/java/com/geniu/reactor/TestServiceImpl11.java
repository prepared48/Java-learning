package com.geniu.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * 重要：使用 CompletableFuture 会导致 Mono 的 timeout 失效。只有 timeout 超过 CompletableFuture 内容才有用
 *
 * @Author: zhongshibo
 * @Date: 2022/8/16 16:13
 */
@Slf4j
public class TestServiceImpl11 implements TestServiceI {
	@Override
	public Mono request() {
		log.info("execute.test.service2");
		CompletableFuture<String> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
			try {
				System.out.println("service2.threadName=" + Thread.currentThread().getName());
				Thread.sleep(500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} catch (Exception e) {
				log.error("error, service2");
			}
			return "市场";
		});

		return Mono.fromFuture(uCompletableFuture).map(name -> {
					return name;
				})
				.timeout(Duration.ofSeconds(1))
				;
	}
}
