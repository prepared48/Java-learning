package com.geniu.jvm.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 *
 * @Author: zhongshibo
 * @Date: 2021/4/25 22:02
 */
public class OOMObject {

    public byte[] placeholder = new byte[64 * 1024];

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) {
        try {
            fillHeap(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
