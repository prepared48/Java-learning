package com.geniu.reactor;

import reactor.core.publisher.Mono;

/**
 * @Author: zhongshibo
 * @Date: 2022/8/16 16:13
 */
public interface TestServiceI {
	Mono request();
}
