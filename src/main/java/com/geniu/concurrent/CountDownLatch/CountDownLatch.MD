CountDownLatch 有啥用

可以实现线程等待

使用join实现线程等待的例子
```

while(存在未对账订单){
  // 查询未对账订单
  Thread T1 = new Thread(()->{
    pos = getPOrders();
  });
  T1.start();
  // 查询派送单
  Thread T2 = new Thread(()->{
    dos = getDOrders();
  });
  T2.start();
  // 等待T1、T2结束
  T1.join();
  T2.join();
  // 执行对账操作
  diff = check(pos, dos);
  // 差异写入差异库
  save(diff);
} 
```

问题：每次循环都需要创建新的线程。所以，考虑使用线程池实现，但是线程池的话怎么知道线程是否执行完毕呢？

## 方法一：可以增加一个**计数器**

## 方法二：使用 CountDownLatch

使用 CountDownLatch 的例子

```

// 创建2个线程的线程池
Executor executor = 
  Executors.newFixedThreadPool(2);
while(存在未对账订单){
  // 计数器初始化为2
  CountDownLatch latch = 
    new CountDownLatch(2);
  // 查询未对账订单
  executor.execute(()-> {
    pos = getPOrders();
    latch.countDown();
  });
  // 查询派送单
  executor.execute(()-> {
    dos = getDOrders();
    latch.countDown();
  });
  
  // 等待两个查询操作结束
  latch.await();
  
  // 执行对账操作
  diff = check(pos, dos);
  // 差异写入差异库
  save(diff);
}
```

## 怎么用


```
// 计数器初始化为2
CountDownLatch latch = new CountDownLatch(2);

// 等待两个查询操作结束 
latch.await();
```

## 方法三 使用 CyclicBarrier

```

// 订单队列
Vector<P> pos;
// 派送单队列
Vector<D> dos;
// 执行回调的线程池 
Executor executor =  Executors.newFixedThreadPool(1);
final CyclicBarrier barrier = new CyclicBarrier(2, ()->{
    executor.execute(()->check());
  });
  
void check(){
  P p = pos.remove(0);
  D d = dos.remove(0);
  // 执行对账操作
  diff = check(p, d);
  // 差异写入差异库
  save(diff);
}
  
void checkAll(){
  // 循环查询订单库
  Thread T1 = new Thread(()->{
    while(存在未对账订单){
      // 查询订单库
      pos.add(getPOrders());
      // 等待
      barrier.await();
    }
  });
  T1.start();  
  // 循环查询运单库
  Thread T2 = new Thread(()->{
    while(存在未对账订单){
      // 查询运单库
      dos.add(getDOrders());
      // 等待
      barrier.await();
    }
  });
  T2.start();
}
```


CountDownLatch 和 CyclicBarrier 是 Java 并发包提供的两个非常易用的线程同步工具类，这两个工具类用法的区别在这里还是有必要再强调一下：

CountDownLatch 主要用来解决一个线程等待多个线程的场景，可以类比旅游团团长要等待所有的游客到齐才能去下一个景点；

而 CyclicBarrier 是一组线程之间互相等待，更像是几个驴友之间不离不弃。

除此之外 CountDownLatch 的计数器是不能循环利用的，也就是说一旦计数器减到 0，再有线程调用 await()，该线程会直接通过。

但 CyclicBarrier 的计数器是可以循环利用的，而且具备自动重置的功能，一旦计数器减到 0 会自动重置到你设置的初始值。

除此之外，CyclicBarrier 还可以设置回调函数，可以说是功能丰富。