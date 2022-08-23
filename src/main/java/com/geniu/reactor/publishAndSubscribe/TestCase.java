package com.geniu.reactor.publishAndSubscribe;

import com.geniu.SPEL.TestCity;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.function.Consumer;

/**
 * @Author: zhongshibo
 * @Date: 2022/8/22 21:51
 */
public class TestCase {

	@Test
	public void test1() {
		Consumer<Integer> consumer = s -> System.out.println(s + " : " + Thread.currentThread().getName());

		Flux.range(1, 5)
				.doOnNext(consumer)
				.map(i -> {
					System.out.println("Inside map the thread is " + Thread.currentThread().getName());
					return i * 10;
				})
				.publishOn(Schedulers.newElastic("First_PublishOn()_thread"))
				.doOnNext(consumer)
				.publishOn(Schedulers.newElastic("Second_PublishOn()_thread"))
				.doOnNext(consumer)
				.subscribeOn(Schedulers.newElastic("subscribeOn_thread"))
				.subscribe();
	}

	@Test
	public void testDefaultThread() {
		Flux<String> cities = Flux.just("New York", "London", "Paris", "Amsterdam")
				.map(String::toUpperCase)
				.filter(cityName -> cityName.length() <= 8)
				.map(cityName -> cityName.concat(" City"))
				.log();

		cities.subscribe();
	}

	@Test
	public void testSubscribeThread() {
		Flux<String> cities = Flux.just("New York", "London", "Paris", "Amsterdam")
				.subscribeOn(Schedulers.boundedElastic())
				.map(String::toUpperCase)
				.filter(cityName -> cityName.length() <= 8)
				.map(cityName -> cityName.concat(" City"))
				.map(TestCase::concat)
				.map(TestCase::stringToUpperCase)
				.log();

//		cities.subscribe();
		System.out.println(cities.blockFirst());
	}

	@Test
	public void testPublishOnThread() {
		Flux.just("New York", "London", "Paris", "Amsterdam")
				.map(TestCase::stringToUpperCase)
				.publishOn(Schedulers.boundedElastic())
				.map(TestCase::concat)
				.subscribe();
	}

	private static String stringToUpperCase(String name) {
		System.out.println("stringToUpperCase: " + Thread.currentThread().getName());
		return name.toUpperCase();
	}

	private static String concat(String name) {
		System.out.println("concat: " + Thread.currentThread().getName());
		return name.concat(" City");
	}
}
