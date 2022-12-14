package com.geniu.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @Author: zhongshibo
 * @Date: 2022/11/16 17:32
 */
public class TestZipWith {

	public static void main(String[] args) {
		TestZipWith testZipWith = new TestZipWith();
		Mono<Integer> stringMono = testZipWith.test1();
		Mono<Integer> floatMono = testZipWith.test2();
		Mono<String> map = stringMono.zipWith(floatMono)
				.map(tuple -> {
					Integer t1 = tuple.getT1();
					Integer t2 = tuple.getT2();
					return t1 + ", "  + t2;
				});
		System.out.println("result=> " + map.block());
	}

	public Mono<Integer> test1() {
		Flux<Integer> stringFlux = Flux.range(1, 10)
				.flatMapSequential(key -> {
					print(String.valueOf(key));
					return testCommon(key);
				});
		return stringFlux.elementAt(0, Integer.MAX_VALUE);
	}

	public Mono<Integer> test2() {
		Flux<Integer> stringFlux = Flux.range(10, 20)
				.flatMapSequential(key -> {
					print(String.valueOf(key));
					return testCommon(key);
				});
		return stringFlux.elementAt(0, Integer.MAX_VALUE);
	}

	public Mono<Integer> testCommon(Integer key) {
		return Mono.just(key);
	}

	public void print(String name) {
		System.out.println(name + "=>" + new Date());
	}
}
