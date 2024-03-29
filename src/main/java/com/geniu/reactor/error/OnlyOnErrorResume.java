package com.geniu.reactor.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * input=1
 * input=2
 * 21:28:12.636 [main] INFO com.geniu.reactor.error.OnlyOnErrorResume - onErrorResume
 * sum=2
 *
 * @Author: zhongshibo
 * @Date: 2022/8/22 20:07
 */
@Slf4j
public class OnlyOnErrorResume {

	public static void main(String[] args) {
		Flux.range(1, 5)
				.doOnNext(i -> System.out.println("input=" + i))
				.map(i -> i == 2 ? i / 0 : i)
				.map(i -> i * 2)
				.onErrorResume(err -> {
					log.info("onErrorResume");
					return Flux.empty();
				})
				.reduce((i, j) -> i + j)
				.doOnNext(i -> System.out.println("sum=" + i))
				.block();
	}
}
