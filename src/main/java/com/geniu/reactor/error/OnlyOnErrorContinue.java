package com.geniu.reactor.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * input=1
 * input=2
 * 21:27:55.300 [main] INFO com.geniu.reactor.error.OnlyOnErrorContinue - onErrorContinue=2
 * input=3
 * input=4
 * input=5
 * sum=26
 *
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
