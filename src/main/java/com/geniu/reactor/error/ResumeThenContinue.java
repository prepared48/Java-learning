package com.geniu.reactor.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 *
 * PS：不会执行 onErrorResume
 *
 * input=1
 * input=2
 * 21:28:27.051 [main] INFO com.geniu.reactor.error.ResumeThenContinue - onErrorContinue=2
 * input=3
 * input=4
 * input=5
 * sum=26
 *
 * @Author: zhongshibo
 * @Date: 2022/8/22 20:09
 */
@Slf4j
public class ResumeThenContinue {

	public static void main(String[] args) {
		Flux.range(1, 5)
				.doOnNext(i -> System.out.println("input=" + i))
				.map(i -> i == 2 ? i / 0 : i)
				.map(i -> i * 2)
				.onErrorResume(err -> {
					log.info("onErrorResume");
					return Flux.empty();
				})
				.onErrorContinue((err, i) -> {
					log.info("onErrorContinue={}", i);
				})
				.reduce((i, j) -> i + j)
				.doOnNext(i -> System.out.println("sum=" + i))
				.block();
	}

}
