package com.geniu.concurrent.future;

import lombok.extern.slf4j.Slf4j;

/**
 * Runnable 三无产品
 */
@Slf4j
public class MyThread implements Runnable {
	@Override
	public void run() {
		log.info("my thread");
	}
}