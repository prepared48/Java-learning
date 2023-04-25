package com.geniu.reactor.service.orchestration.serial;

import com.geniu.reactor.service.orchestration.invoker.Invoker1;
import com.geniu.reactor.service.orchestration.invoker.Invoker2;
import reactor.core.publisher.Mono;

/**
 * 同时返回invoker1的结果
 *
 * @Author: zhongshibo
 * @Date: 2023/4/25 15:21
 */
public class FlatMapSerial2 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		Mono<String> invoke1 = Invoker1.invoke1();
		Mono<String> result = invoke1.flatMap(p -> {
			return Invoker2.invoke2().map(s -> {
				return p + s;
			});
		});

		// invoker2耗时：3469（串行）
		System.out.println("result: " + result.block() + ", 耗时：" + (System.currentTimeMillis() - start));
	}

}
