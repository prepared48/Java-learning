package com.geniu.concurrent.javaConcurrencyInPractice.chapter4;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * @Author: zhongshibo
 * @Date: 2021/2/19 21:50
 */
@NotThreadSafe
public class MutablePoint {

    int x;

    int y;

    public MutablePoint(MutablePoint loc) {
        this.x = loc.x;
        this.y = loc.y;
    }
}
