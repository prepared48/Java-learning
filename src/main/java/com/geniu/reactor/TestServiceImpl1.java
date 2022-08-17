package com.geniu.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @Author: zhongshibo
 * @Date: 2022/8/16 16:13
 */
@Slf4j
public class TestServiceImpl1 implements TestServiceI{
	@Override
	public Mono request() {
		log.info("execute.test.service1, threadName = ", Thread.currentThread().getName());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return Mono.just(new TestUser("testname1"));
	}
}
