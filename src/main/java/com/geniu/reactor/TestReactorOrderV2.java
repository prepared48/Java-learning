package com.geniu.reactor;

import com.geniu.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 调用服务内 使用 fromSupplier，结果 时间是多个服务之和
 *
 * @Author: zhongshibo
 * @Date: 2022/8/16 16:20
 */
@Slf4j
public class TestReactorOrderV2 {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		TestServiceI testServiceImpl4 = new TestServiceImpl4();
		TestServiceI testServiceImpl5 = new TestServiceImpl5();
		TestServiceI testServiceImpl6 = new TestServiceImpl6();
		List<TestServiceI> serviceIList = new ArrayList<>();
		serviceIList.add(testServiceImpl4);
		serviceIList.add(testServiceImpl5);
		serviceIList.add(testServiceImpl6);

		Flux<Mono<TestUser>> monoFlux = Flux.fromIterable(serviceIList)
				.map(service -> {
					return service.request();
				});
		Flux flux = monoFlux.flatMapSequential(mono -> {
			return mono.map(user -> {
						TestUser testUser = JsonUtil.parseJson(JsonUtil.toJson(user), TestUser.class);
						if (Objects.nonNull(testUser) && StringUtils.isNotBlank(testUser.getName())) {
							return testUser;
						}
						return null;
					})
					.onErrorContinue((err, i) -> {
						log.info("onErrorContinue={}", i);
					});
		});
		Mono mono = flux.elementAt(0, Mono.just("default"));
		Object block = mono.block();
		System.out.println(block + "blockFirst 执行耗时ms：" + (System.currentTimeMillis() - startTime));
	}
}
