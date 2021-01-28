package com.geniu.resilience4j;

/**
 * @Author: zhongshibo
 * @Date: 2021/1/28 10:29
 */
public class BackendService {

    public String doSomething(String param1, String param2) {
        // doSomething for retry
        return param1 + param2;
    }
}
