## SpringCloud Nacos快速搭建,数分钟走向时代潮流（未完）

## 1.前言

```
Nacos是近期在Java界的一个热点，所以我也想去凑凑热闹了解一下， 感觉也确实不错。更多体验还是让大家去慢慢发现。
```

## 2.部署

### 1. 环境准备

```
 64 bit OS，支持 Linux/Unix/Mac/Windows，推荐选用 Linux/Unix/Mac。
 64 bit JDK 1.8+；下载 & 配置。
 Maven 3.2.x+；下载 & 配置。
```

### 1. 单机部署

在Nacos的官方给了两种部署方式

- **1.通过源代码编译（推荐）**

  ```
    作为一个Javaer应该要有探索精神，clone一下源码再自己编译打包
    这有利于查看源代码，更换启动方式都有帮助
  ```

  ```
  	git clone https://github.com/alibaba/nacos.git
  	cd nacos/
  	mvn -Prelease-nacos clean install -U  
  	ls -al distribution/target/
  
  	// change the $version to your actual path
  	cd distribution/target/nacos-server-$version/nacos/bin
  ```

  ```
  	-- Linux/Unix/Mac启动命令(standalone代表着单机模式运行，非集群模式):
  
  	sh startup.sh -m standalone
  ```

  ```
  	# Windows启动命令：
  	cmd startup.cmd
  ```

  ```
    启动成功后在浏览器访问：http://localhost:8848/nacos
  ```

**我就知道你们想看界面UI**

首页默认是配置界面

![Nacos管理界面](https://oscimg.oschina.net/oscnet/2c21a9b259e3eb579807eca9e3564adf445.jpg)

服务列表界面

![服务列表界面](https://oscimg.oschina.net/oscnet/10223d67b23c3062c9d70e48cb2ca40594d.jpg)

历史配置版本界面（支持回滚）

![历史配置](https://oscimg.oschina.net/oscnet/faf743f0c820e8e0179c4b810138ca33a76.jpg)

对比Eureka+Config 这样的界面更加友好直观， OpenApi我不详细介绍，官网的介绍更加详细 [传送门](https://nacos.io/zh-cn/docs/open-API.html)

- **2.通过下载编译后jar包（不推荐，不说那么多，自己去官网看）**

  ```
    [传送门](https://nacos.io/zh-cn/docs/quick-start.html "传送门")
  ```

**2. 集群部署（敬请期待）**

## 3. 代码及配置

以下代码都可以通过文章结尾的传送门里面的Git找到

**代码结构(这里建了两个模块为了验证配置中心与服务发现+远程调用)**

![代码结构](https://oscimg.oschina.net/oscnet/0e1c266043eae62c69ba56691d84cc1d2cf.jpg)

**spring-cloud-nacos 默认读取名字为bootstrap.yml的配置，如果用application.yml请注意配置@PropertySource**

#### provider的bootstrap配置

```
spring:
  application:
    name: nacos-provider
  cloud:
      nacos:
        discovery:
          server-addr: 127.0.0.1:8848
        config:
          group: test-group
          server-addr: 127.0.0.1:8848
          file-extension: yml
server:
  port: 8080
```

#### consumer的bootstrap.yml配置

```
server:
  port: 8081
spring:
  application:
    name: nacos-consumer
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
      config:
        group: test-group
        server-addr: 127.0.0.1:8848
        file-extension: yml
```

基本配置解释：

- 服务发现配置：

  ```
  	#服务发现注册地址
  	nacos.cloud.discovery.server-addr
  ```

- 配置中心配置：

  ```
  	#配置中心的group属性，用于配置分组
  	nacos.cloud.config.group
  
  	#配置中心的地址
  	nacos.cloud.config.server-addr
  
  	#对应配置的格式 (与控制台的配置对应)
  	nacos.cloud.config.file-extension
  
  	#集群名称
  	nacos.cloud.config.cluster-name
  
  	#配置的字符集（默认UTF-8）
  	nacos.cloud.config.encode
  
  	#读取配置超时时间（单位：毫秒 默认3000毫秒）
  	nacos.cloud.config.timeout
  
  	#命名空间（每一个命令空间可以有200个配置）
  	nacos.cloud.config.namespace
  
  	#accessKey（官网没有介绍，阿里云RAM的AccessKey, 现在找不到怎么用，目测未来阿里云会推出Nacos服务）
  	nacos.cloud.config.access-key
  
  	#secretKey（官网没有介绍，阿里云RAM的SccessKey,现在找不到怎么用，目测未来阿里云会推出Nacos服务）
  	nacos.cloud.config.secret-key
  ```

### 传送门：

1. [Nacos官网](https://nacos.io/)
2. [Demo Git地址](https://gitee.com/msgcode/spring-cloud-nacos-demo)