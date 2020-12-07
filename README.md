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

## 多线程

二、如何设置线程池线程数？
线程池究竟设成多大是要看你给线程池处理什么样的任务，任务类型不同，线程池大小的设置方式也是不同的。
任务一般可分为：CPU密集型、IO密集型、混合型，对于不同类型的任务需要分配不同大小的线程池。

**CPU密集型任务**

尽量使用较小的线程池，一般为**CPU核心数+1**。
因为CPU密集型任务使得CPU使用率很高，若开过多的线程数，只能增加上下文切换的次数，因此会带来额外的开销。

**IO密集型任务**

可以使用稍大的线程池，一般为**2\*CPU核心数**。
IO密集型任务CPU使用率并不高，因此可以让CPU在等待IO的时候去处理别的任务，充分利用CPU时间。

**混合型任务**

可以将任务分成IO密集型和CPU密集型任务，然后分别用不同的线程池去处理。
只要分完之后两个任务的执行时间相差不大，那么就会比串行执行来的高效。
因为如果划分之后两个任务执行时间相差甚远，那么先执行完的任务就要等后执行完的任务，最终的时间仍然取决于后执行完的任务，而且还要加上任务拆分与合并的开销，得不偿失。