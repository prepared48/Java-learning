package com.geniu.concurrent;

/**
 * 原子性问题说明，两个线程的并发，也不能保证原子性.
 * 怎么保证latter一定比former大1 ？
 *
 * @Author: zhongshibo
 * @Date: 2020/7/24 06:53
 */
public class ThreadSafeSample02 {

	public static void main(String[] args) throws InterruptedException {
		int sharedState = 0;
		ThreadSafeSample02 sample = new ThreadSafeSample02();
		Thread threadA = new Thread() {
			public void run() {
				sample.nonSafeAction(sharedState);
			}
		};
		Thread threadB = new Thread() {
			public void run() {
				sample.nonSafeAction(sharedState);
			}
		};
		threadA.start();
		threadB.start();
		threadA.join();
		threadB.join();
	}

	public void nonSafeAction(int sharedState) {
		while (sharedState < 100000) {
			int former = sharedState++;
			int latter = sharedState;
			if (former != latter - 1) {
				System.out.printf("Observed data race, former is " +
						former + ", " + "latter is " + latter);
			}
		}
	}
}
