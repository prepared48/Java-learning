package com.geniu.reactor;

import com.geniu.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: zhongshibo
 * @Date: 2022/8/16 16:20
 */
@Slf4j
public class TestReactorOrder2 {

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
//				.subscribeOn(Schedulers.parallel())
				.flatMapSequential(service -> {
					if(service instanceof TestServiceImpl2) {
						service = null;
						return Mono.empty();
					}
					return service.request().map(user -> {
								TestUser testUser = JsonUtil.parseJson(JsonUtil.toJson(user), TestUser.class);
								if (Objects.nonNull(testUser) && StringUtils.isNotBlank(testUser.getName())) {
									return testUser;
								}
								throw new RuntimeException("错误数据");
							})
							.doOnError(e-> {
								log.error("error, reactorOrder2", e);
							})
							.onErrorContinue((err, i) -> {
								log.info("onErrorContinue111={}", i);
							});
				})
				.onErrorContinue((err, i) -> {
					log.info("onErrorContinue222={}", i);
				});
		Mono mono = monoFlux.elementAt(0);
//		Object blockFirst = flux.blockFirst();
		Object block = mono.block();
		System.out.println(block + "blockFirst 执行耗时ms：" + (System.currentTimeMillis() - startTime));

	}
}
