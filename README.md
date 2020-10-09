# Dubbo-Study
**一、SOA**  service oriented architecture

1. SOA 是一种思想，面向服务的架构
2. SOA定位：如何设计项目，让开发更有效率



SOA架构的代表项目：

​Dubbo: 基于RPC 通信

​Springcloud: 基于HTTP的REST方式，能够与Springboot、spring framework、spring Data、Spring Batch完美的结合，这些对微服务来讲是至关重要的。

**二、RPC**: Remote procedure call Protocal

客户端通过互联网调用远程服务器，不知道远程服务器的具体实现，只知道远程服务器提供了什么功能。

远程服务器只提供服务列表，没有功能具体是实现，

**三、Dubbo的重要组成部分**
1. Provider : 提供者,服务的发布方
2. Consumer: 消费者,服务的调用方
3. Container: Dubbo容器,依赖于Spring 容器
4. Registry: 注册中心,当Container启动时把所有可以提供的服务列表上Registry中进行注册
