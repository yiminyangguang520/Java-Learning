# RabbitMQ 与 SpringBoot2.X 整合

![96](https://upload.jianshu.io/users/upload_avatars/9167995/52e82a8d-9d05-43c9-956d-a6cc20e9ee23?imageMogr2/auto-orient/strip|imageView2/1/w/96/h/96)

 

[HmilyMing](https://www.jianshu.com/u/fc4084f90d6d)

 

关注

 1.0 2019.02.18 08:49 字数 617 阅读 318评论 0喜欢 10

- publisher-confirms ，实现一个监听器用于监听 broker 端给我们返回的确认请求： RabbitTemplate.ConfirmCallback

- publisher-returns, 保证消息对 broker 端是可达的，如果出现路由键不可达的情况，则使用监听器对不可达的消息进行后续处理，保证消息的路由成功： RabbitTemplate.ReturnCallback

- 注意一点，在发送消息的时候对 template 进行配置 mandatory = true 保证监听有效。在生产端还可以配置其他属性，比如发送重试、超时时间、次数、间隔等

### 代码实现：

消费端代码地址：`https://github.com/hmilyos/rabbitmqdemo.git` rabbitmq-springboot/rabbitmq-springboot-consumer 项目下

生产端代码地址：`https://github.com/hmilyos/rabbitmqdemo.git` rabbitmq-springboot/rabbitmq-springboot-product 项目下

#### 通用依赖：

```
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.4.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.6</version>
        </dependency>
```

#### 消费端：

核心配置在配置文件里面

- 首先配置手工确认签收模式，用于ACK 的手工处理，这样我们可以保证消息的可靠性送达，或者在消费端消费失败的时候可以做到重回队列（不建议重回队列）、根据业务记录日志等处理。

- 设置消费端的监听个数和最大个数，用于控制消费端的并发情况

```
spring.rabbitmq.addresses=192.168.0.7:5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
spring.rabbitmq.virtual-host=/
spring.rabbitmq.connection-timeout=15000

spring.rabbitmq.listener.simple.acknowledge-mode=manual
spring.rabbitmq.listener.simple.concurrency=5
spring.rabbitmq.listener.simple.max-concurrency=10
```

主配置：

```
@Configuration
@ComponentScan({"com.hmily.*"})
public class MainConfig {

}
```

消费端的监听 `RabbitListener` 这个注解很好用！！！

`RabbitListener` 是一个组合注解，里面可以注解配置 。
`@QueueBinding @Queue @Exchange` 直接通过这个组合注解一次性搞定消费端交换机、队列、绑定、路由、并且配置监听功能等。

```
Message 使用的是  org.springframework.messaging.Message
@Slf4j
@Component
public class RabbitReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1",
                    durable="true"),
            exchange = @Exchange(value = "exchange-1",
                    durable="true",
                    type= "topic",
                    ignoreDeclarationExceptions = "true"),
            key = "springboot.*"
    )
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        log.info("--------------------------------------");
        log.info("消费端Payload: " + message.getPayload());
        Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        channel.basicAck(deliveryTag, false);
    }
}
```

#### 生产端

##### 生产端的核心配置

配置文件：

```
spring.rabbitmq.addresses=192.168.0.7:5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
spring.rabbitmq.virtual-host=/
spring.rabbitmq.connection-timeout=15000

spring.rabbitmq.publisher-confirms=true
spring.rabbitmq.publisher-returns=true
# return 的时候代表消息不可达，设置 broker 不自动删除该消息，
# 而是返回到生产端，让我们进行一些后续的处理
spring.rabbitmq.template.mandatory=true
```

`spring.rabbitmq.template.mandatory=true` 的意思是： return 的时候代表消息不可达，设置 broker 不自动删除该消息，而是返回到生产端，让我们进行一些后续的处理

主配置类：

```
@Configuration
@ComponentScan({"com.hmily.*"})
public class MainConfig {

}
```

消费端发送代码

```
@Slf4j
@Component
public class RabbitSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    //回调函数: confirm确认
    final ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info("correlationData: " + correlationData);
            log.info("ack: " + ack);
            if(!ack){
                log.info("异常处理....");
            }
        }
    };
    
    //回调函数: return返回
    final ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode,
                String replyText, String exchange, String routingKey) {
            log.info("return exchange: {}, routingKey: {}, replyCode: {}, replyText: {}",
                    exchange, routingKey, replyCode, replyText);
        }
    };
    
    //发送消息方法调用: 构建Message消息
    public void send(Object message, Map<String, Object> properties) throws Exception {
        MessageHeaders mhs = new MessageHeaders(properties);
        Message<Object> msg = MessageBuilder.createMessage(message, mhs);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //id + 时间戳 全局唯一
        String id = UUID.randomUUID().toString();
        log.info("id: {}", id);
        CorrelationData correlationData = new CorrelationData(id);
        rabbitTemplate.convertAndSend("exchange-1", "springboot.abc", msg, correlationData);
    }
}
```

写个单元测试用例

```
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqSpringbootProductApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private RabbitSender rabbitSender;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    
    @Test
    public void testSender1() throws Exception {
         Map<String, Object> properties = new HashMap<>();
         properties.put("number", "12345");
         properties.put("send_time", simpleDateFormat.format(new Date()));
         rabbitSender.send("Hello RabbitMQ For Spring Boot!", properties);
    }
}
```

运行单元测试



![img](https://upload-images.jianshu.io/upload_images/9167995-7183ee96c876e7cd?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp)

image

看消费端的日志



![img](https://upload-images.jianshu.io/upload_images/9167995-10ba23df094bb1c3?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp)

image

修改一下发送时的 routingkey ，模拟发送失败



![img](https://upload-images.jianshu.io/upload_images/9167995-0fbb6e02f096d2fb?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp)

image

就进入 returnCallback



![img](https://upload-images.jianshu.io/upload_images/9167995-c6d6bce670179b7a?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp)

image

#### 发送一个 Java 实体

先在消费端 声明一些队列、交换机、routingKey 的配置

```
spring.rabbitmq.listener.order.queue.name=queue-2
spring.rabbitmq.listener.order.queue.durable=true
spring.rabbitmq.listener.order.exchange.name=exchange-2
spring.rabbitmq.listener.order.exchange.durable=true
spring.rabbitmq.listener.order.exchange.type=topic
spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions=true
spring.rabbitmq.listener.order.key=springboot.*
```

消费端：

```
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.order.queue.name}",
                    durable="${spring.rabbitmq.listener.order.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange.name}",
                    durable="${spring.rabbitmq.listener.order.exchange.durable}",
                    type= "${spring.rabbitmq.listener.order.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.order.key}"
    )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload Order order,
                               Channel channel,
                               @Headers Map<String, Object> headers) throws Exception {
        log.info("--------------------------------------");
        log.info("消费端order: " + order.getId());
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        channel.basicAck(deliveryTag, false);
    }
这里面有个特别需要注意的地方，Payload 里面的路径要跟 生产端的实体路径完全一致，要不然会找到不到这个类的！！！
我这里为了简便就不写一个 common.jar 了，在实际开发里面，这个 java bean 应该放在 common.jar  里面
```

注意实体要实现 Serializable 序列化接口，要不然发送消息会失败的！！

```
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private String id;
    private String name;


}
```

生产端 照样跟着写一个发消息的方法

```
    //发送消息方法调用: 构建自定义对象消息
    public void sendOrder(Order order) throws Exception {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //id + 时间戳 全局唯一
        String id = UUID.randomUUID().toString();
        log.info("sendOrder id: {}", id);
        CorrelationData correlationData = new CorrelationData(id);
        rabbitTemplate.convertAndSend("exchange-2", "springboot.def", order, correlationData);
    }
```

写单元测试

```
    @Test
    public void testSender2() throws Exception {
         Order order = new Order("001", "第一个订单");
         rabbitSender.sendOrder(order);
    }
```

运行单元测试



![img](https://upload-images.jianshu.io/upload_images/9167995-ba42a10e942c6dd3?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp)

image

验证 Java 实体消息发送成功



![img](https://upload-images.jianshu.io/upload_images/9167995-bbd877ee11a42950?imageMogr2/auto-orient/strip%7CimageView2/2/w/945/format/webp)