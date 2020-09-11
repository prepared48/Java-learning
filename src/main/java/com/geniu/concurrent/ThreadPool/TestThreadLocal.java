package com.geniu.concurrent.ThreadPool;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class TestThreadLocal {

    public static void main(String[] args) {

        ThreadLocal<String> local = new ThreadLocal<>();

        Random random = new Random();

        IntStream.range(0, 5).forEach(
                a -> new Thread(() -> {
                    local.set(a + " " + random.nextInt(10));
                    System.out.println("线程和local值分别是：" + local.get());

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start()
        );
    }
}
