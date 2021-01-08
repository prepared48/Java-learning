CompletableFuture

默认情况下 CompletableFuture 会使用公共的 ForkJoinPool 线程池，这个线程池默认创建的线程数是 CPU 的核数
（也可以通过 JVM option:-Djava.util.concurrent.ForkJoinPool.common.parallelism 来设置 ForkJoinPool 线程池的线程数）。

但是也不一定就使用ForkJoinPool，要看（cpu的核数-1）是否大于1，如果大于1，使用过 ForkJoinPool，否则，创建普通线程执行。

cpu核数 = Runtime.getRuntime().availableProcessors(); // 4

源码如下：

```
    // 是否使用 useCommonPool，如果（cpu的核数-1）大于1，使用过ForkJoinPool，否则，创建普通线程执行。
    private static final boolean useCommonPool =
            (ForkJoinPool.getCommonPoolParallelism() > 1);

    /**
     * Default executor -- ForkJoinPool.commonPool() unless it cannot
     * support parallelism.
     */
    private static final Executor asyncPool = useCommonPool ?
        ForkJoinPool.commonPool() : new ThreadPerTaskExecutor();
    
    /** Fallback if ForkJoinPool.commonPool() cannot support parallelism */
    static final class ThreadPerTaskExecutor implements Executor {
        public void execute(Runnable r) { new Thread(r).start(); }
    }
```

CompletableFuture 可以应用在异步编程场景中。

比如经典的泡茶：

任务1：洗水壶、烧开水

任务2：洗茶壶、洗茶杯、拿茶叶

任务3：泡茶

其中任务1和任务2可以并行执行；--使用 `supplyAsync` 方法提交异步任务

任务3必须等待任务1和任务2完成之后执行。--使用`thenCombine`完成等待。

```
//任务1：洗水壶->烧开水
CompletableFuture<Void> f1 = 
  CompletableFuture.runAsync(()->{
  System.out.println("T1:洗水壶...");
  sleep(1, TimeUnit.SECONDS);

  System.out.println("T1:烧开水...");
  sleep(15, TimeUnit.SECONDS);
});
//任务2：洗茶壶->洗茶杯->拿茶叶
CompletableFuture<String> f2 = 
  CompletableFuture.supplyAsync(()->{
  System.out.println("T2:洗茶壶...");
  sleep(1, TimeUnit.SECONDS);

  System.out.println("T2:洗茶杯...");
  sleep(2, TimeUnit.SECONDS);

  System.out.println("T2:拿茶叶...");
  sleep(1, TimeUnit.SECONDS);
  return "龙井";
});
//任务3：任务1和任务2完成后执行：泡茶
CompletableFuture<String> f3 = 
  f1.thenCombine(f2, (__, tf)->{
    System.out.println("T1:拿到茶叶:" + tf);
    System.out.println("T1:泡茶...");
    return "上茶:" + tf;
  });
//等待任务3执行结果
System.out.println(f3.join());

void sleep(int t, TimeUnit u) {
  try {
    u.sleep(t);
  }catch(InterruptedException e){}
}
// 一次执行结果：
T1:洗水壶...
T2:洗茶壶...
T1:烧开水...
T2:洗茶杯...
T2:拿茶叶...
T1:拿到茶叶:龙井
T1:泡茶...
上茶:龙井
```

CompletableFuture 提交的任务会按照顺序执行，如果最后提交的任务执行时间比较长，效果不好。尽量把

执行时间长的任务先提交。或者配置实际线程数，设置合理的顺序。

问题：TestCompletableFuture.java 输出如下

```
start： Fri Jan 08 09:54:09 CST 2021
任务4, 线程名字ForkJoinPool.commonPool-worker-1
任务1, 线程名字ForkJoinPool.commonPool-worker-2
任务2, 线程名字ForkJoinPool.commonPool-worker-3
end: Fri Jan 08 09:54:09 CST 2021
任务3, 线程名字ForkJoinPool.commonPool-worker-2
task1 end: Fri Jan 08 09:54:10 CST 2021
task2 end: Fri Jan 08 09:54:11 CST 2021
task3 end: Fri Jan 08 09:54:13 CST 2021
task4 end: Fri Jan 08 09:54:13 CST 2021
```

能看到它创建了 3 个线程。但是什么呢？

| cpu核数 = Runtime.getRuntime().availableProcessors(); // 4

所以启动 3 个线程。


https://time.geekbang.org/column/article/94604