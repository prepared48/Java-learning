package com.geniu.reactor;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Function;

/**
 * map() 功能是映射（执行一个转转）
 *
 * @Author: zhongshibo
 * @Date: 2022/8/19 11:12
 */
public class TestReactorMap {

	public static void main(String[] args) {
		Function<String, String > mapper = String::toUpperCase;
		Flux<String> inFlux = Flux.just("hello", ".", "com");
		Flux<String> outFlux = inFlux.map(mapper);
		// reactor 测试包提供的测试方法
		StepVerifier.create(outFlux)
				.expectNext("HELLO", ".", "COM")
				.expectComplete()
				.verify();
	}
}
