package com.geniu.reactor.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * onErrorResume 模拟 onErrorContinue
 *
 * @Author: zhongshibo
 * @Date: 2022/8/22 20:10
 */
@Slf4j
public class MimicContinueUseResume {

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
				)
				.reduce((i, j) -> i + j)
				.doOnNext(i -> System.out.println("sum=" + i))
				.block();
	}
}
