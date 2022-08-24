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
 * 不抛出异常，使用 onErrorResume 继续下一个
 *
 * @Author: zhongshibo
 * @Date: 2022/8/16 16:20
 */
@Slf4j
public class TestReactorOrder4 {

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
				.flatMapSequential(service -> {
					return service.request().map(user -> {
								TestUser testUser = JsonUtil.parseJson(JsonUtil.toJson(user), TestUser.class);
								return Objects.nonNull(testUser) && StringUtils.isNotBlank(testUser.getName()) ? testUser : null;
							})
							.onErrorResume(err -> {
								return Mono.empty();
							});
				});
		Mono mono = monoFlux.elementAt(0);
//		Object blockFirst = flux.blockFirst();
		Object block = mono.block();
		System.out.println(block + "blockFirst 执行耗时ms：" + (System.currentTimeMillis() - startTime));
	}
}
