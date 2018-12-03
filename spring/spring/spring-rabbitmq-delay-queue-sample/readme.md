# spring-rabbit
spring rabbitmq integrate

spring对rabbitmq支持的一些示例,主要包括:
发送消息确认
消费消息确认
死信队列

TestConfirm测试类

# RabbitMQ(六)使用Dead Letter(死信队列)进行延时发送

在我们实际业务中,经常会碰到需要延时处理的问题.例如A操作5分钟后触发B操作.这些也可以使用定时器来实现.

考虑以下情况.

1.客户端提交订单A.

2.服务端F新增订单A,通过Rabbitmq发送新增订单消息M1到服务F2.返回新增成功到客户端(M模块不影响主业务).

3.客户端收到新增成功后,取消订单A.

4.服务端F取消订单A,发送取消订单消息M2到模块M

5.偶尔出现服务F2先消费M2,再消费M1的情况.


上面5里面出现的问题怎么解决呢?

1)保证有序性

1.M1,M2使用同一个queue, 发送到queue的有序性得到保证.

2.M1,M2使用同一个consumer, queue到消费者的有序性得到保证.

2)M2更新操作延时处理

1.预留足够M1处理的时间

2.不一定适用所有业务


我们这里就适用方案2),延时处理. 此方案不一定适用其他场景,知道怎么使用后,可以自己取舍.


RabbitMQ 官方没有提供延时处理功能.但是推荐了github上的一个插件.我感觉那个插件还没使用Dead Letter简单,所以就适用Dead Letter来实现.


Dead Letter 官方介绍 http://www.rabbitmq.com/dlx.html

通过官网介绍,可以发现Dead Letter 本来不是用于延时发送的, 而是处理 消息过期,消息被拒绝,队列超出限制大小 等情况.

所以使用 Dead Letter 来延迟发送 算是个 曲线救国.

下面是spring对于dead letter的配置

```xml
	<rabbit:queue name="dead-letter-queue">
		<rabbit:queue-arguments>
			<entry key="x-message-ttl" value="5000" value-type="java.lang.Long" />
			<entry key="x-dead-letter-exchange" value="common-exchange" />
			<entry key="x-dead-letter-routing-key" value="common-queue" />
		</rabbit:queue-arguments>
	</rabbit:queue>
	
	<rabbit:queue name="common-queue" />
	<rabbit:direct-exchange name="common-exchange"
		durable="false" auto-delete="false" id="common-exchange">
		<rabbit:bindings>
			<rabbit:binding queue="common-queue" />
		</rabbit:bindings>
	</rabbit:direct-exchange>
```

 声明一个队列,名称为dead-letter-queue,里面指定了消息存活时间(毫秒),出现死信情况后,转发到的 exchange和routing key  

在消费者监听中,只需要监听 转发后的queue即可

```xml
	<rabbit:listener-container
		connection-factory="connectionFactory" acknowledge="manual" >
		<rabbit:listener queues="common-queue" ref="receiveConfirmTestListener" />
	</rabbit:listener-container>
```

dead-letter-queue的队列没有消费监听,所以过了消息存活时间后,就会自动转发到common-exchange.

common-exchange和common-queue就是普通的exchange和queue.