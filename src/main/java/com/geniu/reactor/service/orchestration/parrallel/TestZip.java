package com.geniu.reactor.service.orchestration.parrallel;

import com.geniu.reactor.service.orchestration.invoker.Invoker1;
import com.geniu.reactor.service.orchestration.invoker.Invoker2;
import reactor.core.publisher.Mono;

/**
 * @Author: zhongshibo
 * @Date: 2023/4/25 15:39
 */
public class TestZip {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Mono<String> invoke1 = Invoker1.invoke1();
		Mono<String> invoker2 = Invoker2.invoke2();
		Mono<String> result = Mono.zip(invoke1, invoker2)
				.map(s-> {
					String t1 = s.getT1();
					String t2 = s.getT2();
					return String.format("invoke1:%s, invoke2: %s", t1, t2);
				});
		// invoker1invoker2耗时：2650 （并行）
		System.out.println("result: " + result.block() + "，耗时：" + (System.currentTimeMillis() - start));
	}
}
