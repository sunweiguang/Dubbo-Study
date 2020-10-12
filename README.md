# Dubbo-Study
**一、SOA**  service oriented architecture

1. SOA 是一种思想，面向服务的架构

2. SOA定位：如何设计项目，让开发更有效率

   

SOA架构的代表项目：

​	<u>Dubbo</u>: 基于RPC 通信

​	<u>Springcloud</u>: 基于HTTP的REST方式，能够与Springboot、spring framework、spring Data、Spring Batch完美的结合，这些对微服务来讲是至关重要的。

**二、RPC**: Remote procedure call Protocal

客户端通过互联网调用远程服务器，不知道远程服务器的具体实现，只知道远程服务器提供了什么功能。

远程服务器只提供服务列表，没有功能具体是实现，

**三、Dubbo的重要组成部分**

1. Provider : 提供者,服务的发布方
2. Consumer: 消费者,服务的调用方
3. Container: Dubbo容器,依赖于Spring 容器
4. Registry: 注册中心,当Container启动时把所有可以提供的服务列表上Registry中进行注册
5. 所有的角色都可以单独在独立的服务器上，所以必须遵循特定的协议

**四、运行原理**

1. 启动容器，相当于启动Dubbo的Provider，启动后会去注册中心进行注册。注册所有可以提供的服务列表

2. 在Consumer启动后会去Registry中获取服务列表和Provider的地址。

3. 当Provider有修改后，注册中心会把消息推送给Consumer

   使用了观察者设计模式（又叫发布/订阅模式）

4. 根据获取到的provider地址，真实调用Provider中提供的服务列表

   在Consumer方使用了代理设计模式，创建了一个Provider方类的一个代理对象，通过代理对象获取Provider中真实功能，起到保护Provider

   *在Dubbo中RPC就是使用了动态代理方法将Provider中的功能保护起来了*

5. Consumer和Provider每间隔一分钟想Monitor发送一次统计信息，统计信息包含访问次数、频率等信息。

<img src="http://dubbo.apache.org/img/architecture.png" alt="img" style="zoom: 67%;" />

**五、Dubbo支持的注册中心**

1. Zookeeper注册中心

   支持网络集群，依赖于Zookeeper的稳定性

2. Redis注册中心

   优点：性能高

   缺点：要求服务器时间同步，用于检查心跳

3. Multicast注册中心

   优点：免中心化

   缺点：依赖于网络拓扑和路由，跨机房有风险

4.  simple注册中心

   只适用于测试环境，不支持集群



分布式：将一个服务拆分成多个部分分别部署

集   群：将一个服务多份部署，分担压力



**六、Dubbo支持的协议**

1. Dubbo 协议

   官方推荐的协议，本质是通过NIO和线程池进行处理

   大文件传输是可能出现文件传输失败的问题

2. Rmi协议，基于TCP协议

   JDK提供的远程方法调用协议，不需要额外导入配置文件

   缺点：偶尔连接失败

3. Hessian协议

   基于Http协议

   需要Hessian.jar,并在短连接是开销大，性能低

**七、Dubbo中Provider搭建**