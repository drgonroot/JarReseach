#### Zqm196 speed4j的 知识线路

1、StopWatch 
  * 统计某次运行时长等其他统计数据
  * 
2、读配置创建
3、log 日志 --> slf4j
   
流程:
* 通过StopWatch的stop的方法触发日志写入
* log方法 将stopWatch加入到queue,且触发收集线程
* 收集线程触发定时器
* 定时器主要任务是清空队列某个时期的数据，进行统计写log

**日志结构设计**
* 内部通用 Log， 因为很多自己逻辑
* 内部log 主要是实现写数据 核心 log(StopWatch sw)
* 
* 具体对外写出日志数据由Slf4jLog 实现日志写入。。。。  