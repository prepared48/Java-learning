package com.geniu.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @Author: zhongshibo
 * @Date: 2022/8/16 16:13
 */
@Slf4j
public class TestServiceImpl3 implements TestServiceI{
	@Override
	public Mono request() {
		log.info("execute.test.service3 = ", Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return Mono.just(new TestUser(""));
	}
}
