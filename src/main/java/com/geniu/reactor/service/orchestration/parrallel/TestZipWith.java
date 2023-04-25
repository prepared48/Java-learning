package com.geniu.reactor.service.orchestration.parrallel;

import com.geniu.reactor.service.orchestration.invoker.Invoker1;
import com.geniu.reactor.service.orchestration.invoker.Invoker2;
import reactor.core.publisher.Mono;

/**
 * @Author: zhongshibo
 * @Date: 2023/4/25 15:39
 */
public class TestZipWith {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Mono<String> invoke1 = Invoker1.invoke1();
		Mono<String> invoker2 = Invoker2.invoke2();
		Mono<String> result = invoke1.zipWith(invoker2)
				.map(s -> {
					return String.format("{invoke1-> %s}, {invoke2-> %s}", s.getT1(), s.getT2());
				});
		// invoker1invoker2耗时：2469 （并行）
		System.out.println("result: " + result.block() + "，耗时：" + (System.currentTimeMillis() - start));
	}
}
