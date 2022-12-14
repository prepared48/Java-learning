package com.geniu.reactor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: zhongshibo
 * @Date: 2022/11/25 16:10
 */
@Slf4j
public class TestSwitchIfEmpty {

	public static void main(String[] args) {
		Mono stringMono = getLocationCacheV3("hello")
				.switchIfEmpty(Mono.defer(()->getLocationFromMap("")));

		System.out.println(stringMono.block());

	}

	public static Mono getLocationCacheV3(String key) {
		CompletableFuture completableFuture = null;

		return Mono.fromSupplier(()-> {
					return test();
				})
				.doOnError(e -> {
					log.error("getLocationCacheV3.result---from cache error: ", e);
				})
				.doFinally(e -> {
					log.info("getLocationCacheV3.result.total");
				});
	}

	private static <T> T test() {
		return null;
	}

	private static Mono getLocationFromMap(String  locationRequest) {
		return Mono.fromSupplier(()-> {
			return test2();
		});
	}

	private static String test2() {
		return "db";
	}
}
