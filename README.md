# cloud2020
学习SpringCloud微服务协调整理治理框架

# SpringCloud学习笔记

## SpringCloud和SpringBoot是什么关系：

SpringBoot专注于快速方便的开发单个个体微服务。

SpringCloud是关注全局的微服务协调整理治理框架，它将SpringBoot开发的一个个单体微服务整合并管理起来，为各个微服务之间提供，配置管理、服务发现、断路器、路由、微代理、事件总线、全局锁、决策竞选、分布式会话等等集成服务。

SpringBoot可以离开SpringCloud独立使用开发项目，但是***SpringCloud离不开SpringBoot***，属于依赖的关系。

**SpringBoot专注于快速、方便的开发单个微服务个体，SpringCloud关注全局的服务治理框架。**



## SpringCloud和Dubbo最大的区别：

**SpringCloud抛弃了Dubbo的RPC通信，采用的是基于HTTP的REST方式。**

严格来说，这两种方式各有优劣，虽然从一定程度上来说，后者牺牲了服务调用的性能，但也避免了上面提到的原生RPC带来的问题，而且REST相比RPC更为灵活，服务提供方和调用方的依赖只依靠一纸契约，不存在代码级别的强依赖，这在强调快速演化的微服务环境下，显得更加合适。



## SpringCloud相关网址：

官网：http://projects.spring.io/spring-cloud/

官网（中文）：https://springcloud.cc/spring-cloud-netflix.html

开发API说明：http://cloud.spring.io/spring-cloud-static/Dalson.SR1/

开发API说明（中文）：https://springcloud.cc/spring-cloud-dalston.html

springcloud中国社区：http://springcloud.cn/

springcloud中文网：https://springcloud.cc/







服务注册与发现、服务调用、服务熔断、负载均衡、服务降级、服务消息队列、配置中心管理、服务网关、服务监控、全链路追踪、自动化构建部署、服务定时任务调度操作





服务注册中心：Eureka【停更】、Zookeeper、Consul、Nacos

服务调用：Ribbon、LoadBalancer

服务调用2：Feign【停更】、OpenFeign

服务降级：Hystrix【停更进维】、resilience4j、sentinel

服务网关：Zuul【停更】、Zuul2、gateway

服务配置：Config【停更】、Nacos

服务总线：Bus【停更】、Nacos



​								         ——> 约定 > 配置 > 编码

4、微服务架构编码构建 ——> IDEA新建project工作空间

​									     ——> Rest微服务工程构建



小总结：1、建module；2、改POM；3、写YML；4、主启动；5、业务类；



热部署（生产环境必须关闭热部署）：

1、子模块添加maven依赖：

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

2、将下段配置粘贴进聚合父类总工程的pom.xml里

```
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <fork>true</fork>
                <addResources>true</addResources>
            </configuration>
        </plugin>
    </plugins>
</build>
```

3、setting -》 Build -》 compiler -》 Automatically show first error in editor、Display notification on build completion、Build project automatically、Compile independent modules in parallel 这几个选项全部打勾。

等等，具体idea配置热部署步骤，自行百度



Eureka服务注册中心集群，互相注册，相互守望



微服务健康检查：/actuator/health



服务发现Discovery：对于注册进eureka里面的微服务，可以通过服务发现来获得该服务的信息。

Eureka自我保护：一句话，某时刻某一个微服务不可用了，Eureka不会立刻清理，依旧会对改微服务的信息进行保存，属于CAP里面的AP分支



Consul 是一套开源的分布式服务发现和配置管理系统，由 HashiCorp 公司用Go语言开发。

提供了微服务系统中的服务治理、配置中心、控制总线等功能。这些功能中的每一个都可以根据需要单独使用，也可以一起使用构建全方位的服务网格，总之Consul提供了一种完整的服务网格解决方案。

它具有很多优点，包括：基于 raft 协议，比较简洁；支持健康检查，同时支持 HTTP 和 DNS 协议 支持跨数据中心的 WAN 集群 提供图形界面 跨平台，支持 Linux、Mac、Windows

consul下载地址：https://www.consul.io/downloads.html



<u>CAP理论关注粒度是数据，而不是整体系统设计的策略</u>

Eureka：AP架构设计（高可用、分区容错性），语言（Java），服务健康检查（可配支持），对外暴露接口（HTTP）

Zookeeper：CP架构设计（一致性、分区容错性），语言（Go），服务健康检查（支持），对外暴露接口（HTTP/DNS）

Consul ：CP架构设计（一致性、分区容错性），语言（Java），服务健康检查（支持），对外暴露接口（客户端）





<u>LB负载均衡（Load Balance）是什么</u>

简单的说就是将用户的请求平摊的分配到多个服务上，从而达到系统的HA（高可用）。常见的负载均衡有软件Nginx，LVS，硬件F5等。

<u>Ribben本地负载均衡客户端 VS Nginx服务端负载均衡区别</u>

Nginx是服务器负载均衡，客户端所有请求都会交给nginx，然后由nginx实现转发请求。即负载均衡是有服务端实现的。

Ribbon本地负载均衡，在调用微服务接口时候，会在注册中心上获取注册信息服务列表之后缓存到JVM本地，从而在本地实现RPC远程服务调用技术。（一句话：负载均衡+RestTemplate调用）

<u>集中式LB</u>

即在服务的消费方和提供方之间使用独立的LB设施（可以是硬件，如F5，也可以是软件，如nginx），由该设施负责把访问请求通过某种策略转发至服务的提供方；

<u>进程内LB</u>

将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选择出一个合适的服务器。Ribbon就属于进程内LB，它只是一个类库，集成于消费方进程，消费方通过它来获取到服务提供方的地址。



Ribbon在工作时分成两步

第一步先选择EurekaServer，它优先选择在同一个区域内负载较少的server。

第二步再根据用户指定的策略，在从server取到的服务注册列表中选择一个地址。

其中Ribbon提供了多种策略：比如轮询、随机和根据响应时间加权。



<u>Ribbon中IRule的落地实现：根据特定算法中从服务列表中选取一个要访问的服务</u>（com.netflix.loadbalancer.RoundRobinRule【轮询，默认】、com.netflix.loadbalancer.RandomRule【随机】、com.netflix.loadbalancer.RetryRule【先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内会进行重试，获取可用的服务】、WeightedResponseTimeRule【对RoundRobinRule的扩展，响应速度越快的实例选择权重越大，越容易被选择】、BestAvailableRule【会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务】、AvailabilityFilteringRule【先过滤掉故障实例，再选择并发较小的实例】、ZoneAvoidanceRule【默认规则，复合判断server所在区域的性能和server的可用性选择服务器】）

RoundRobinRule 轮询负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务重启动后rest接口计数从1开始



<u>Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端，负载均衡工具。</u>

简单的说，Ribbon是Netflix发布的开源项目，主要功能是提供**客户端的软件负载均衡算法和服务调用**。Ribbon客户端组件提供一系列完善的配置项如连接超时，重试等。简单的说，就是在配置文件中列出Load Balancer（简称LB）后面所有的机器，Ribbon会自动的帮助你基于某种规则（如简单轮询，随机连接等）去连接这些机器。我们很容易使用Ribbon实现自定义的负载均衡算法 。



Feign是一个声明式的Web服务客户端，让编写Web服务客户端变得非常简单，只需要创建一个接口并在接口上添加注解即可。

Feign旨在使编写Java Http客户端变得更容易

<u>openFeign超时控制：</u>

默认Feign客户端只等待一秒钟，但是服务端处理需要超过1秒钟，导致Feign客户端不想等待了，直接返回报错。为了避免这样的情况，有时候我们需要设置Feign客户端的超时控制。





Hystrix重要概念 ——> 服务降级（fallback） 、服务熔断（break）、服务限流（flowlimit）

Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时、异常等，Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。

<u>服务降级</u> ——> 服务器忙，请稍后重试，不让客户端等待并立刻返回一个友好提示，fallback

<u>哪些情况会出现服务降级：</u>

​	**程序运行异常、超时、服务熔断触发服务降级、线程池/信号量打满也会导致服务降级**

<u>服务熔断</u> ——> 访问量达到上线时，直接拒绝访问，然后调用服务降级的方法并返回友好提示（服务降级 -> 进而熔断 -> 恢复调用链路）

<u>服务限流</u> ——> 限制高并发等操作，控制每次进入量，排队有序进行



Gateway新一代网关，三大核心概念：Route（路由）、Predicate（断言）、Filter（过滤）





*<u>application.yml 是用户级的资源配置项；**bootstrap.yml 是系统级的资源配置项，优先级更高</u>***



Spring Cloud Bus 是用来将分布式系统的节点与轻量级消息系统链接起来的的框架，它整合了Java的事件处理机制和消息中间件的功能。Spring Cloud Bus 目前支持RabbitMQ和Kafka。

Spring Cloud Bus 能管理和传播分布式系统间的消息，就像一个分布式执行器，可用于广播状态更改，事件推送等，也可以当作微服务间的通信通道

什么是总线：

在微服务架构的系统中，通常会使用轻量级的消息代理来构建一个共用的消息主题，并让系统中所有微服务实例都连接上来。由于该主题中产生的消息会被所有实例监听和消费，所以称它为消息总线。在总线上的各个实例，都可以方便地广播一些需要让其他连接在该主题上的实例都知道的消息。

基本原理

ConfigClient实例都监听MQ中同一个topic（默认是springCloudBus）。当一个服务刷新数据的时候，它会把这个信息放入到Topic中，这样其它监听同一个Topic的服务就能得到通知，然后去更新自身的配置。



## 90_Stream之group解决消息重复消费

故障现象：重复消费

导致原因：默认分组group是不同的，组流水号不一样，被认为不同组，可以消费

自定义配置分组，自定义配置分为同一个组，解决重复消费问题

原理：微服务应用放置于同一个group中，就能够保证消息只会被其中一个应用消费一次。不同的组是可以消费的，同一个组内会发生竞争关系，只有其中一个可以消费。



## 91_Stream之消息持久化



## 92_Sleuth是什么【SpringCloud Sleuth分布式请求链路跟踪】

Github访问地址：<https://github.com/spring-cloud/spring-cloud-sleuth>

SpringCloud Sleuth提供了一套完整的服务跟踪的解决方案，在分布式系统中提供追踪解决方案并且兼容支持了zipkin



## 93_Cloud Alibaba简介

Github访问地址：https://github.com/alibaba/spring-cloud-alibaba

【中文访问地址：<https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md>】



## 96_Nacos简介和下载

Nacos是一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。

Nacos：Dynamic Naming and Configuration Service

Nacos就是注册中心 + 配置中心的组合，等价于 Nacos = Eureka + Config + Bus



Github访问地址：http://github.com/alibaba/Nacos（版本查看：<https://github.com/alibaba/nacos/tags>）

官方文档：<https://nacos.io/zh-cn/>



## 97_Nacos之服务提供者注册

Spring Cloud Alibaba官方学习文档：<https://spring.io/projects/spring-cloud-alibaba#learn>



## 100_Nacos服务注册中心对比提升

Nacos与其他注册中心特性对比

|                 | Nacos                      | Eureka      | Consul            | CoreDNS | Zookeeper   |
| --------------- | -------------------------- | ----------- | ----------------- | ------- | ----------- |
| 一致性协议      | CP+AP                      | AP          | CP                | /       | CP          |
| 健康检查        | TCP/HTTP/MYSQL/Client Beat | Client Beat | TCP/HTTP/gRPC/CMD | /       | Client Beat |
| 负载均衡        | 权重/DSL/metadata/CMDB     | Ribbon      | Fabio             | RR      | /           |
| 雪崩保护        | 支持                       | 支持        | 不支持            | 不支持  | 不支持      |
| 自动注销实例    | 支持                       | 支持        | 不支持            | 不支持  | 支持        |
| 访问协议        | HTTP/DNS/UDP               | HTTP        | HTTP/DNS          | DNS     | TCP         |
| 监听支持        | 支持                       | 支持        | 支持              | 不支持  | 支持        |
| 多数据中心      | 支持                       | 支持        | 支持              | 不支持  | 不支持      |
| 跨注册中心      | 支持                       | 不支持      | 支持              | 不支持  | 不支持      |
| SpringCloud集成 | 支持                       | 支持        | 支持              | 不支持  | 不支持      |
| Dubbo集成       | 支持                       | 不支持      | 不支持            | 不支持  | 支持        |
| K8s集成         | 支持                       | 不支持      | 支持              | 支持    | 不支持      |

Nacos支持AP和CP两种模式的切换。

Nacos中CP和AP模式切换命令：

curl -X PUT 'SNACOS_SERVER:8848/nacos/v1/ns/operator/switches?entry=serverMode&value=CP'



## 101_Nacos之服务注册中心

```yaml
#${spring.application.name}-${spring.profile.active}.${spring.cloud.nacos.config.file-extension}
# nacos-config-client-dev.yaml
```

## 102_Nacos服务配置中心之命名空间分组和DataID三者的关系

1、类似于Java里面的package名和类名，最外层的namespace是可以用于区分部署环境的，Group和DataID逻辑上区分两个目标对象。

2、三者情况：NameSpace > Group > Service > (Cluster1 > instance1 + instance2 + ...) + (Cluster2 > instance1 + instance2 + ...) + ...

3、默认情况：NameSpace=public，Group=DEFAULT_GROUP，默认Cluster是DEFAULT4

4、Nacos的Config配置方案有：DataID方案、Group方案、Namespace方案

## 106_Nacos集群/架构说明

Nacos集群和持久化配置（重要），

官方文档：<https://nacos.io/zh-cn/docs/cluster-mode-quick-start.html>

默认Nacos使用嵌入式数据库实现数据的存储。所以，如果启动多个默认配置下的Nacos节点，数据存储是存在一致性问题的。为了解决这个问题，Nacos采用了集中式存储的方式来支持集群化部署，目前只支持MySQL的存储。

Nacos支持三种部署模式

1、单机模式 - 用于测试个单机试用。

2、集群模式 - 用于生产环境，确保高可用。

3、多集群模式 - 用于多数据中心场景。







## 149_大厂面试第三季预告片之雪花算法（上）

分布式全局唯一ID以及分布式ID的业务需求：

​	ID生成规则部分硬性要求：

​		1、全局唯一：

​		2、趋势递增：

​		3、单调递增：

​		4、信息安全：

​		5、含时间戳：




