# 消息队列：SpringBoot集成RocketMQ的那些坑（真实有效、附源码）

技术不更新的坑

本着一颗爱自由、爱技术的心，不断在探索技术的路上前进，可是总是有一些坑是需要不断的去踩，去做一些改变来适应这个技术发展飞快的时代。
我用上了SpringBoot2.0+和JDK10。这都是比较新的技术，迟早要踩坑的。在升级了框架后，要更新使用的RocketMQ，那么问题就来了，各种问题层出不穷，好在我都一一解决了。值得高兴。

# 一、SpringBoot2.0+框架

首先你要有一个SpringBoot2.0+框架，这个框架已经出来很久了，如果你还没有用上那么你的技术确实需要更新了。
怎么下载使用这个框架，我在这里就不多说了，相信看到这个帖子的多少都是已经有了基础的。

# 二、RocketMQ代码

其实Apache官方，已经给我们提供了rocketmq-spring-boot-starter给大家直接集成到SpringBoot框架中。
广泛给出的地址：[rocketmq-spring-boot-starter详情](https://github.com/apache/rocketmq-externals/tree/master/rocketmq-spring-boot-starter)

在这个页面，有几点特别需要说明。

# 三、Apache埋的坑

1、关于pom.xml文件的引用

如下图
![这里写图片描述](https://img-blog.csdn.net/2018081717382857?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dkMjAxNDYxMA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

```
<!--在pom.xml中添加依赖-->
<dependency>
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>spring-boot-starter-rocketmq</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

pom无法引用
因为这个Apache并没有把这份代码传到中央仓库。
也就是说这个地方是没有的。
![这里写图片描述](https://img-blog.csdn.net/20180817174044855?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dkMjAxNDYxMA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

这个时候，你如想要引这个包怎么办呢？需要自己打包，传到私服上去。
你需要先将这份代码下载下来。
在这个代码目录下执行打包操作：

    ~/Aliyun/rocketmq-externals/rocketmq-spring-boot-starter on  master ⌚ 17:44:02

先执行：

    mvn clean

再执行：

    mvn package -Dmaven.test.skip=true

注意：坑
报错了：

    [ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.1:compile (default-compile) on project spring-boot-starter-rocketmq: Fatal error compiling: java.lang.ExceptionInInitializerError: com.sun.tools.javac.code.TypeTags -> [Help 1]

看下自己的Maven配的JDK的版本；（我的是JDK10）

    1
    2
    3
    4
    5
    6
    7

回去把Pom改一下，把框架升到2.0+重新打包，jdk建议暂时先不改。最小的改动，能够成功就好了，免得出现其他的问题。

![这里写图片描述](https://img-blog.csdn.net/20180817175017159?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dkMjAxNDYxMA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

打包成功，发到私服。

![这里写图片描述](https://img-blog.csdn.net/20180817175341817?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dkMjAxNDYxMA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

然后再在项目中引用你自己私服的项目。

       <!-- RocketMQ起-->
          <!--在pom.xml中添加依赖-->
          <dependency>
            <groupId>org.apache.rocketmq</groupId>
            <artifactId>spring-boot-starter-rocketmq</artifactId>
            <version>1.0.0-SNAPSHOT</version>
          </dependency>
    
          <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-messaging</artifactId>
            <version>5.1.2.RELEASE</version>
          </dependency>
    
          <dependency>
            <groupId>org.apache.rocketmq</groupId>
            <artifactId>rocketmq-client</artifactId>
            <version>4.3.0</version>
            <exclusions>
              <exclusion>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-api</artifactId>
              </exclusion>
            </exclusions>
          </dependency>
    
          <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.9.7</version>
          </dependency>
          
          <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <version>2.1.0.RELEASE</version>
            <optional>true</optional>
          </dependency>

2、关于spring.rocketmq.name-server的坑

看下图：
![这里写图片描述](https://img-blog.csdn.net/20180817173315896?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dkMjAxNDYxMA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

注意：
如果你是SpringBoot2.0+的框架，或者是JDK10。
你需要将你自己的项目配置文件中的，spring.rocketmq.name-server改成
**spring.rocketmq.nameServer。注意是nameServer**。
不然就会报各种稀奇古怪的bug。
3、关于启动报内存不足的错

在安装启动Name Server和Broker的时候，一定要修改配置文件，不然内存会爆炸。

    Native memory allocation (mmap) failed to map 8589934592 bytes for committing reserved memory

这里写图片描述

将下面的配置文件根据你的需要改
我这里以前默认是Xms4g，都是g，我修改到m就行了。

    JAVA_OPT="${JAVA_OPT} -server -Xms256m -Xmx256m -Xmn128m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"

# 四、下面提供源码1.spring-boot-starter-rocketmq

### 1.spring-boot-starter-rocketmq

[rocketmq-externals项目下有spring-boot-starter-rocketmq](https://github.com/xiongben-tongxue/rocketmq-externals.git)

### 2.生产者

生产者源码：[源码下载地址](https://github.com/xiongben-tongxue/newframe.git)

### 3.消费者源码

消费者源码：[源码下载地址](https://github.com/xiongben-tongxue/newframe-externals.git)

# 五、成功效果图

1、首先要确保Name Server和Broker是启动态

![这里写图片描述](https://img-blog.csdn.net/20180821150222129?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dkMjAxNDYxMA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

然后启动生产者，启动消费者，就可以之间互相发消息！

# 六、reference

https://blog.csdn.net/wd2014610/article/details/81781109