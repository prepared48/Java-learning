package com.geniu.reactor;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;



/**
 * @Author: zhongshibo
 * @Date: 2022/8/19 11:20
 */
public class TestReactorFlatMap {

	public static void main(String[] args) {
		Mono<String> mono1 = new TestServiceImpl10().request();
		Mono<String> mono2 = new TestServiceImpl11().request();


		Mono<String> result = Mono.zip(mono1, mono2)
				.map(tuple -> {
					String result1 = tuple.getT1(); // 获取 mono1 的结果
					String result2 = tuple.getT2(); // 获取 mono2 的结果（如果 mono2 没有返回，该变量为 null）
					if (result2 != null) {
						// 如果 mono2 有返回结果，则进行处理
//						System.out.println("Result 1: " + result1);
//						System.out.println("Result 2: " + result2);
						return result1 + result2;
					} else {
						// 如果 mono2 没有返回结果，则直接返回 mono1 的结果
						return result1;
					}
				})
				.switchIfEmpty(mono1);

		System.out.println(result.block());
	}

	/**
	 * 把字符串转成大写，然后分拆成字母
	 */
	public static void test1() {
		Function<String, Publisher<String>> mapper = s -> Flux.just(s.toUpperCase().split(""));

		Flux<String> inFlux = Flux.just("hellohellohellohellohellohellohellohello", ".", "comabcdefghijklmnopqrstuvwsyz");
		// 这里只能使用 flatMap，因为参数是 Function<T, Publisher<V>> 形式
		Flux<String> outFlux = inFlux.flatMap(mapper);

		List<String> output = new ArrayList<>();
		outFlux.subscribe(output::add);
		System.out.println(output);
	}

	/**
	 * 生成流值的流。
	 * FluxFlatMap 和 FluxMapFuseable 是什么区别？
	 */
	public static void test2() {
		Flux<String> stringFlux = Flux.just("hello word!");
		Function<String, Publisher<String>> mapper = s -> Flux.just(s.toUpperCase().split(""));
		Function<String, String> mapMapper = String::toUpperCase;
		Flux<String> flatMapFlux = stringFlux.flatMap(mapper);

		Flux<String> mapFlux = stringFlux.map(mapMapper);
		System.out.println(flatMapFlux);
		System.out.println(mapFlux);
	}
}
