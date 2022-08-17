package com.geniu.reactor;

import com.geniu.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: zhongshibo
 * @Date: 2022/8/16 16:20
 */
@Slf4j
public class TestReactorOrder {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		TestServiceI testServiceImpl1 = new TestServiceImpl1();
		TestServiceI testServiceImpl2 = new TestServiceImpl2();
		TestServiceI testServiceImpl3 = new TestServiceImpl3();
		List<TestServiceI> serviceIList = new ArrayList<>();
		serviceIList.add(testServiceImpl2);
		serviceIList.add(testServiceImpl3);
		serviceIList.add(testServiceImpl1);

		Flux<Mono> monoFlux = Flux.fromIterable(serviceIList)
				.publishOn(Schedulers.parallel())
				.map(service -> {
					return service.request();
				});


		Flux flux = monoFlux.flatMap(mono -> {
			return mono.map(user -> {
						TestUser testUser = JsonUtil.parseJson(JsonUtil.toJson(user), TestUser.class);
						if (Objects.nonNull(testUser) && StringUtils.isNotBlank(testUser.getName())) {
							return testUser;
						}
						throw new RuntimeException("错误数据");
					})
					.onErrorContinue((err, i) -> {
						log.info("onErrorContinue={}", i);
					});
		});
		Mono mono = flux.elementAt(0);
//		Object blockFirst = flux.blockFirst();
		Object block = mono.block();
		System.out.println(block + "blockFirst 执行耗时ms：" + (System.currentTimeMillis() - startTime));
	}
}
