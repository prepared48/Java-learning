# java-learning

java8 feature learn

1、Lambda expression

- concurrent——多线程相关
- jvm JVM——虚拟机相关
- sources——源码相关
- Redis——Redis相关
- Kafka——Kafka相关
- Elasticsearch——ES相关
- HBase——HBase 相关
- SpringBoot——Spring Boot 相关

## 1 多线程

### 1.1 如何设置线程池线程数？

线程池究竟设成多大是要看你给线程池处理什么样的任务，任务类型不同，线程池大小的设置方式也是不同的。
任务一般可分为：CPU密集型、IO密集型、混合型，对于不同类型的任务需要分配不同大小的线程池。

**CPU密集型任务**

尽量使用较小的线程池，一般为**CPU核心数+1**。
因为CPU密集型任务使得CPU使用率很高，若开过多的线程数，只能增加上下文切换的次数，因此会带来额外的开销。

**IO密集型任务**

可以使用稍大的线程池，一般为**2\*CPU核心数**。
IO密集型任务CPU使用率并不高，因此可以让CPU在等待IO的时候去处理别的任务，充分利用CPU时间。

**混合型任务**

最佳线程数 =CPU 核数 * [ 1 +（I/O 耗时 / CPU 耗时）]

可以将任务分成IO密集型和CPU密集型任务，然后分别用不同的线程池去处理。
只要分完之后两个任务的执行时间相差不大，那么就会比串行执行来的高效。
因为如果划分之后两个任务执行时间相差甚远，那么先执行完的任务就要等后执行完的任务，最终的时间仍然取决于后执行完的任务，而且还要加上任务拆分与合并的开销，得不偿失。

### 1.2 线程间通信

on the way

### 1.3 异步编程

#### 1.3.1 CompletableFuture

默认情况下 CompletableFuture 会使用公共的 ForkJoinPool 线程池，这个线程池默认创建的线程数是 CPU 的核数
（也可以通过 JVM option:-Djava.util.concurrent.ForkJoinPool.common.parallelism 来设置 ForkJoinPool 线程池的线程数）。

但是也不一定就使用ForkJoinPool，要看（cpu的核数-1）是否大于1，如果大于1，使用过ForkJoinPool，否则，创建普通线程执行。


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


### 1.4 获取线程执行结果的几种方式

#### 1.4.1、Callable 线程

    public class FetchAdTask implements Callable<Ad> {
    
        @Override
        public Ad call() throws Exception {
            System.out.println("fetch task");
            sleep(1000L);
            return null;
        }
    }

#### 1.4.2、使用Future，包括 FutureTask、CompletableFuture

    CompletableFuture.get();
    
Future 的优点：可以对任务设置时限，如果超时了，可以取消，然后返回一个默认结果，防止
某一个任务出现问题，导致系统出现问题。

    f.get(timeLeft, TimeUnit.NANOSECONDS);
    
或者通过 invokeAll() 返回限定时间范围内的所有任务的结果。     

    executor.invokeAll(tasks, time, unit);   

#### 1.4.3、使用 CompletionService，

    CompletionService.take();
    
优点：多个 CompletionService 可以共享一个 Executor，因此可以创建一个对于特定计算私有，
又能共享一个公共 Executor 的 ExecutorCompletionService。

# 一些概念

## 1 竞态条件

在并发编程中，由于不恰当的执行时序而出现不正确的

的结果是一种非常重要的情况，它的正式名字就叫竞态条件。

或者可以说：当某个计算的正确性取决于多个线程的交替执行时序时，
那么就会发生竞态条件。

换句话说，就是正确的结果取决于运气。

**最常见的竞态条件类型就是"先检查后执行"**

懒汉式 单例模式 中，就有这个问题。

## 2 重入

重入 意味着获取锁的操作的粒度是"线程"，而不是"调用"。

### 2.1 一种实现方法

为每个锁关联一个获取计数值和一个所有者线程。当计数值为 0 时，

这个锁就被认为是没有任何线程持有。当线程请求一个未被持有的锁时，JVM

将记录下锁的持有者，并且将获取计数值置为 1。如果容一个线程再次获取这个锁，计数值

将递增，而当线程退出同步代码块时，计数器会相应地递减。

## 3 发布

发布一个对象的意思是指，使对象能够在当前作用域之外的代码中使用。

一个简单的发布对象的方法：把对象的引用保存到一个公有的静态变量中。参考：Test0305PublishObject.java

## 4 不可变对象

满足以下条件时，对象才是不可变的：

1、对象创建以后其状态就不能修改；

2、对象的所有域都是final类型；

3、对象是正确创建的（在对象的创建期间，this引用没有逸出）

说明：即使对象中的所有的域都是final类型的，这个对象也仍然是可变的。    

## 5 自旋锁

只有在有理由认为等待时间是非常短的情形下，才使用忙等待。

用于忙等待的锁，称为自旋锁。

## C10K问题

> C10k就是 client 10 000 问题。即"在同时连接服务器的客户端数量超过10 000 个的环境中，
即便硬件性能足够，依然无法正常提供服务"。简言之，就是单机1万个并发连接问题。


## 管程


---

本人博客：https://www.geniu.net/

本人 csdn 博客链接：https://blog.csdn.net/Prepared

