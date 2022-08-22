package com.geniu.reactor.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 使用onErrorResume()和下游的onErrorContinue()模拟onErrorContinue()
 *
 * @Author: zhongshibo
 * @Date: 2022/8/22 20:10
 */
@Slf4j
public class MimicContinueUseResume2 {

	public static void main(String[] args) {
		Flux.range(1, 5)
				.doOnNext(i -> System.out.println("input=" + i))
				.flatMap(i -> Mono.just(i)
						.map(j -> j == 2 ? j / 0 : j)
						.map(j -> j * 2)
						.onErrorResume(err -> {
							System.out.println("onErrorResume");
							return Mono.empty();
						})
						.onErrorStop()
				)
				.onErrorContinue((err, i) -> {
					log.info("onErrorContinue={}", i);
				})
				.reduce((i, j) -> i + j)
				.doOnNext(i -> System.out.println("sum=" + i))
				.block();
	}
}
