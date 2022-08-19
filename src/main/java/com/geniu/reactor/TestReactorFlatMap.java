package com.geniu.reactor;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;



/**
 * @Author: zhongshibo
 * @Date: 2022/8/19 11:20
 */
public class TestReactorFlatMap {

	public static void main(String[] args) {
		test2();
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
