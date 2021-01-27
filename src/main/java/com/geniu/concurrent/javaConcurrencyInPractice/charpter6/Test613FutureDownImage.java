package com.geniu.concurrent.javaConcurrencyInPractice.charpter6;

import com.sun.scenario.effect.ImageData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 程序清单6-13 使用Future等待图像下载
 * Callable创建线程可以获取线程执行的结果
 *
 * @Author: zhongshibo
 * @Date: 2021/1/20 22:16
 */
public class Test613FutureDownImage {

    // 线程池
    private final ExecutorService executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1,
            Runtime.getRuntime().availableProcessors() + 1, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue(1000));

    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() throws Exception {
                List<ImageData> result = new ArrayList<>();
                for (ImageInfo imageInfo : imageInfos) {
                    result.add(imageInfo.downloadImage());
                }
                return result;
            }
        };

        Future<List<ImageData>> future = executor.submit(task);
        renderText(source);
        try {
            List<ImageData> imageData = future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void renderText(CharSequence source) {
    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return null;
    }

}
