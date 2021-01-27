package com.geniu.concurrent.javaConcurrencyInPractice.charpter6;

import com.sun.scenario.effect.ImageData;

import java.util.List;
import java.util.concurrent.*;

/**
 * 使用 CompletionService 获取线程结果
 *
 * @Author: zhongshibo
 * @Date: 2021/1/20 22:30
 */
public class Test615CompletionServiceDownImage {

    private final ExecutorService executor;

    // 构造函数初始化线程池
    public Test615CompletionServiceDownImage(ExecutorService executor) {
        this.executor = executor;
    }

    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<>(executor);
        for (final ImageInfo imageInfo : imageInfos) {
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }
            });
        }
        // 处理text
        renderText(source);
        // 获取线程执行结果

        try {
            for (int t = 0, n = imageInfos.size(); t < n; t++) {
                Future<ImageData> take = completionService.take();
                ImageData imageData = take.get();
                // 渲染图片
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void renderImage(ImageData imageData) {
    }

    private void renderText(CharSequence source) {
    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return null;
    }
}
