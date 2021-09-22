package com.geniu.jvm;

/**
 * -Xms1m -Xmx1m -XX:+UseSerialGC
 *
 * 测试 OOM
 */
public class TestOOM {

    public static void main(String[] args) {
        byte[] bytes = new byte[100 * 1024 * 1024];
    }

}
