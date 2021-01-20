这个包主要介绍并实践《JAVA并发编程实战》中的多线程开发中的例子。


## 获取线程执行结果的几种方式

### 1、Callable 线程

### 2、使用Future，包括 FutureTask、CompletableFuture

    CompletableFuture.get();
    
Future 的优点：可以对任务设置时限，如果超时了，可以取消，然后返回一个默认结果，防止
某一个任务出现问题，导致系统出现问题。

    f.get(timeLeft, TimeUnit.NANOSECONDS);
    
或者通过 invokeAll() 返回限定时间范围内的所有任务的结果。     

    executor.invokeAll(tasks, time, unit);   

### 3、使用 CompletionService，

    CompletionService.take();
    
优点：多个 CompletionService 可以共享一个 Executor，因此可以创建一个对于特定计算私有，
又能共享一个公共 Executor 的 ExecutorCompletionService。