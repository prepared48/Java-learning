package com.geniu.concurrent.javaConcurrencyInPractice.charpter7;

/**
 * 关闭钩子
 * 通过注册一个关闭钩子来停止日志服务
 *
 * @Author: zhongshibo
 * @Date: 2021/1/27 22:20
 */
public class Test0726ShutdownHook {

    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    new Test0726ShutdownHook().new LogService().stop();
                } catch (InterruptedException ignored) {
                    // 处理异常
                }
            }
        });
    }

    class LogService {
        public void stop() throws InterruptedException {
            // stop log
        }
    }
}
