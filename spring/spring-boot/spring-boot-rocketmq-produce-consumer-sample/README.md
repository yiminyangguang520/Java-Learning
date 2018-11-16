# rocketmq-demo

[![License](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

It's a demo project for how to use [spring-boot-starter-rocketmq](https://github.com/apache/rocketmq-externals/tree/master/rocketmq-spring-boot-starter) 

> Note:
>
> if `org.apache.rocketmq:spring-boot-starter-rocketmq:1.0.0-SNAPSHOT` not exists in maven central repository, before you run this demo, you must download [spring-boot-starter-rocketmq](https://github.com/apache/rocketmq-externals/tree/master/rocketmq-spring-boot-starter) and use `mvn clean install` build it by self.



执行以下操作，安装spring-boot-starter-rocketmq到本地仓库

```
git clone https://github.com/apache/rocketmq-externals
cd rocketmq-spring-boot-starter
mvn clean install
```



# 版本冲突问题

由于编译spring-boot-starter-rocketmq时，采用spring boot版本为1.5.7 RELEASE，所以demo中采用spring boot版本也为1.5.7 RELEASE



若编译spring-boot-starter-rocketmq时，采用spring boot版本为2.0.4 RELEASE，则demo中也采用相同版本的spring boot时，启动demo中的消费者时会报错如下，暂未找到原因：

```
Field rocketMQTemplate in com.aqlu.rocketmq.demo.ProducerApplication required a bean of type 'org.apache.rocketmq.spring.starter.core.RocketMQTemplate' that could not be found.
	- Bean method 'rocketMQTemplate' in 'RocketMQAutoConfiguration' not loaded because @ConditionalOnBean (types: org.apache.rocketmq.client.producer.DefaultMQProducer; SearchStrategy: all) did not find any beans of type org.apache.rocketmq.client.producer.DefaultMQProducer

```



由于spring-boot-starter-rocketmq中采用的rocketmq-client版本为4.2.0，故在搭建rocketmq服务时，需采用4.2.0版本，如果采用4.3.0版本，运行demo中的生产者时，则会报错如下：

```
com.alibaba.rocketmq.client.exception.MQClientException: No route info of this topic,
```

解决方案：网上所有方案都试验过，均未成功，故还是保持rocketmq版本一致