# 基于Dubbo的school项目（dubbo-spring-boot-starter）

school-dubbo-springboot是一个基于Dubbo的课程管理系统，服务通过RPC调用，使得系统更加解耦，变得更加灵活，同时Springboot能极大的降低Dubbo的配置，能使你能够快速得开发项目。

## 一、运行工具、技术与环境

运行环境：JDK 8，Maven 3.3+

技术栈：SpringBoot 2.0+、Dubbo 2.6+、Druid、Thymeleaf、ZooKeeper 3.3+

工具：IntelliJ IDEA、谷歌浏览器

## 二、Springboot快速集成Dubbo关键的依赖
```maven
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.0.3.RELEASE</version>
</parent>

<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>0.2.0</version>
</dependency>
```

## 三、使用步骤
1.将项目导入IntelliJ IDEA，maven加载jar包。

2.先启动生产者provider项目，再启动消费者consumer项目。

3.打开浏览器，输入网址[http://localhost:8091](http://localhost:8091)即可浏览。

## 四、总结
关于school，我已经整合了很多次，我希望每一次整合新技术都能够给我带来进步，与思想上的成长。

------

smirk小泽   
2018 年 06月29日    
