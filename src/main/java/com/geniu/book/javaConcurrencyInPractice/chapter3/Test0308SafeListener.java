package com.geniu.book.javaConcurrencyInPractice.chapter3;

import org.apache.commons.configuration.event.EventSource;

import java.awt.*;
import java.util.EventListener;

/**
 * 使用工厂方法来防止 this 引用在构造过程中逸出
 *
 * @Author: zhongshibo
 * @Date: 2021/2/22 20:38
 */
public class Test0308SafeListener {

    private final EventListener listener;

    private Test0308SafeListener() {
        listener = new EventListener() {
            public void onEvent(Event e) {
                doSomething(e);
            }

            private void doSomething(Event e) {
            }
        };
    }

    public static Test0308SafeListener newInstance(EventSource source) {
        Test0308SafeListener safe = new Test0308SafeListener();
//        source.registerListener(safe.listener);
        return safe;
    }
}
