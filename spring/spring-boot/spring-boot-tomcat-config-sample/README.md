# Spring Boot中为Tomcat配置多个监听端口

标签（空格分隔）： Spring Tomcat SpringBoot

---

今天有同事问”Spring Boot依赖的Embedded的Tomcat能不能同时监听多个端口？“
stand-alone的tomcat当然是可以的，Tomcat的架构中，一个Connecter监听一个端口。
如果是stand-alone的Tomcat，只需要在server.xml中添加一个<connector>即可，如下：
```xml
<Connector port="8080" redirectPort="8443" acceptCount="100" debug="0" connectionTimeout="20000" />
<Connector port="8081" redirectPort="8443" acceptCount="100" debug="0" connectionTimeout="20000" />
<Connector port="8082" redirectPort="8443" acceptCount="100" debug="0" connectionTimeout="20000" />
```
上面配置了3个Connector，分别监听 8080，8081，8082 3个端口。

Spring Boot使用了Embedded Tomcat，同时提供了EmbeddedServletContainerCustomizer接口让用户对各种EmbeddedServletContainer进行配置。
该接口只要一个方法：
```java
public void customize(
			ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer)
```
ConfigurableEmbeddedServletContainer接口代表了可配置的EmbeddedServletContainer。
这个接口有如下实现：
![EmbeddedServletContainer继承图][1]
TomcatEmbeddedServletContainerFactory就是我们需要的类。
TomcatEmbeddedServletContainerFactory#addAdditionalTomcatConnectors(Connector connector) 可以为Tomcat开启多个端口。
核心代码如下：
```java
@SpringBootApplication
public class TomcatConfigApplication implements EmbeddedServletContainerCustomizer {

	public static void main(String[] args) {
		SpringApplication.run(TomcatConfigApplication.class, args);
	}

	@Value("${server.additional-ports}")
	String ports;

	@Override
	public void customize(
			ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {

		if (ports != null) {
			// 判断如果是Tomcat才进行如下配置
			if (configurableEmbeddedServletContainer instanceof TomcatEmbeddedServletContainerFactory) {
				// 转类型为TomcatEmbeddedServletContainerFactory
				TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) configurableEmbeddedServletContainer;

				String[] portsArray = ports.split(",");
				for (String portStr : portsArray) {
					int port = Integer.parseInt(portStr);
					// Tomcat中，一个Connecter监听一个端口
					// 指定协议为HTTP/1.1
					Connector httpConnector = new Connector("HTTP/1.1");
					httpConnector.setPort(port);
					tomcat.addAdditionalTomcatConnectors(httpConnector);
				}

			}
		}
	}
}
```
测试一下：

启动日志里会显示
2017-10-31 21:48:14.961  INFO 81863 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http) 8081 (http) 8082 (http) 8083 (http)

再用curl分别请求这三个端口，如下图请求都是成功的
![curl][2]

TomcatEmbeddedServletContainerFactory还可以配置Tomcat其他很多属性，详情参考api。

完整代码：https://github.com/pkpk1234/spring-boot-tomcat-config

  [1]: https://ip.freep.cn/593396/Jietu20171031-213842.jpg
  [2]: https://ip.freep.cn/593396/Jietu20171031-215109.jpg
