package com.geniu.reactor.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * @Author: zhongshibo
 * @Date: 2022/8/22 20:08
 */
@Slf4j
public class OnlyOnErrorContinue {

	public static void main(String[] args) {
		Flux.range(1, 5)
				.doOnNext(i -> System.out.println("input=" + i))
				.map(i -> i == 2 ? i / 0 : i)
				.map(i -> i * 2)
				.onErrorContinue((err, i) -> {
					log.info("onErrorContinue={}", i);
				})
				.reduce((i, j) -> i + j)
				.doOnNext(i -> System.out.println("sum=" + i))
				.block();
	}
}
