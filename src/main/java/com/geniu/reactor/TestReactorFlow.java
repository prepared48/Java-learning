package com.geniu.reactor;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * @Author: zhongshibo
 * @Date: 2023/7/26 16:29
 */
public class TestReactorFlow {

	public static void main(String[] args) {
//		Mono<User> mono1 = mono1();
//		Function<User, Mono<Response>> mono2 = user -> {
//			System.out.println("execute mono2");
//			return Mono.just(new Response("mono2", user.getUserName()));
////			return Mono.empty();
//		};
//		Function<String, Mono<Response>> mono3 = (tag) -> {
//			System.out.println("execute mono3" + ": " + tag);
//			return Mono.just(new Response("mono3"));
//		};
//		Object block = mono1
//				.flatMap(user -> {
//					if (user != null) {
//						return mono2.apply(user);
//					} else {
//						return mono3.apply("flatmap");
//					}
//				})
//				.block();
//		System.out.println("result= " + block);

		case1();
		case2();
	}

	private static void case2() {
		// 测试 Mono1 返回 null 的情况
		Mono<User> mono1 = Mono.empty();
		Function<User, Mono<ResponseV2>> mono2 = user -> Mono.just(new ResponseV2("Hello, " + user.getUserName()));
		Mono<ResponseV2> mono3 = Mono.just(new ResponseV2("The user is empty."));
		Mono<ResponseV2> responseMono = mono1.switchIfEmpty(Mono.just(new User("empty")))
				.flatMap(user -> {
					if (!user.getUserName().equals("empty")) {
						// 当 user 对象不为空时，执行 mono2 处理逻辑
						return mono2.apply(user);
					} else {
						// 当 user 对象为空时，返回 mono3 处理结果
						return mono3;
					}
				});

		responseMono.subscribe(response -> System.out.println(response.getContent())); // 输出 "The user is empty."
	}

	private static void case1() {
		// 测试 Mono1 返回 userName 为 "zhangsan" 的情况
		Mono<User> mono1 = Mono.just(new User("zhangsan"));
		Function<User, Mono<ResponseV2>> mono2 = user -> Mono.just(new ResponseV2("Hello, " + user.getUserName()));
		Mono<ResponseV2> mono3 = Mono.just(new ResponseV2("The user is empty."));

		Mono<ResponseV2> responseMono = mono1.flatMap(user -> {
			if (user != null) {
				// 当 user 对象不为空时，执行 mono2 处理逻辑
				return mono2.apply(user);
			} else {
				// 当 user 对象为空时，返回 mono3 处理结果
				return mono3;
			}
		});
		responseMono.subscribe(response -> System.out.println(response.getContent())); // 输出 "Hello, zhangsan"
	}

	private static Mono<User> mono1() {
		System.out.println("execute mono1");
//		return Mono.just(new User("test"));
		return Mono.empty();
	}

	private static Mono<ResponseV2> mono2(User user) {
		System.out.println("execute mono2");
		return Mono.just(new ResponseV2("mono2" + user.getUserName()));
	}

	private static Mono<ResponseV2> mono3(String tag) {
		System.out.println("execute mono3" + ": " + tag);
		return Mono.just(new ResponseV2("mono3"));
	}

	@Data
	@AllArgsConstructor
	public static class User {
		private String userName;
	}

	@Data
	@AllArgsConstructor
	public static class Response {
		private String base;
		private String userName;

		public Response(String base) {
			this.base = base;
		}
	}

	@Data
	@AllArgsConstructor
	public static class ResponseV2 {
		private String content;
	}
}
