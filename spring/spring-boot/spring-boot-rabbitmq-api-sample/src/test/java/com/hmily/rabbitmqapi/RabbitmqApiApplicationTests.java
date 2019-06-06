package com.hmily.rabbitmqapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmily.rabbitmqapi.spring.domain.Order;
import com.hmily.rabbitmqapi.spring.domain.Packaged;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApiApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(RabbitmqApiApplicationTests.class);

	@Test
	public void contextLoads() {
	}

	@Autowired
	private RabbitAdmin rabbitAdmin;

	@Test
	public void testAdmin() throws Exception {
		rabbitAdmin.declareExchange(new DirectExchange("test.direct", false, false));
		rabbitAdmin.declareExchange(new TopicExchange("test.topic", false, false));
		rabbitAdmin.declareExchange(new FanoutExchange("test.fanout", false, false));
		rabbitAdmin.declareQueue(new Queue("test.direct.queue", false));
		rabbitAdmin.declareQueue(new Queue("test.topic.queue", false));
		rabbitAdmin.declareQueue(new Queue("test.fanout.queue", false));

        rabbitAdmin.declareBinding(new Binding("test.direct.queue",
                Binding.DestinationType.QUEUE,
                "test.direct", "direct", new HashMap<>()));

        rabbitAdmin.declareBinding(
                BindingBuilder
                        .bind(new Queue("test.topic.queue", false))        //直接创建队列
                        .to(new TopicExchange("test.topic", false, false))    //直接创建交换机 建立关联关系
                        .with("user.#"));    //指定路由Key

        rabbitAdmin.declareBinding(
                BindingBuilder
                        .bind(new Queue("test.fanout.queue", false))
                        .to(new FanoutExchange("test.fanout", false, false)));

        //清空队列数据
        rabbitAdmin.purgeQueue("test.topic.queue", false);
	}


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void testSendMessage() throws Exception {
        //1 创建消息
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().put("desc", "信息描述..");
        messageProperties.getHeaders().put("type", "自定义消息类型..");
        Message message = new Message("Hello RabbitMQ".getBytes(), messageProperties);

        rabbitTemplate.convertAndSend("topic001", "spring.amqp", message, new MessagePostProcessor() {
            @Override  // message 就是上面声明要发送的消息
            public Message postProcessMessage(Message message) throws AmqpException {
                log.info("------添加额外的设置---------");
                message.getMessageProperties().getHeaders().put("desc", "额外修改的信息描述");
                message.getMessageProperties().getHeaders().put("attr", "额外新加的属性");
                return message;
            }
        });
    }

    @Test
    public void testSendMessage2() throws Exception {
        //1 创建消息
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("text/plain");
        Message message = new Message("mq 消息1234   --spring.abc".getBytes(), messageProperties);

        rabbitTemplate.send("topic001", "spring.abc", message);

        rabbitTemplate.convertAndSend("topic001", "spring.amqp", "hello object message send!   -spring.amqp");
        rabbitTemplate.convertAndSend("topic002", "rabbit.abc", "hello object message send!  -rabbit.abc");
    }

    @Test
    public void testSendMessage4Text() throws Exception {
        //1 创建消息
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("text/plain");
        Message message = new Message("mq 消息1234".getBytes(), messageProperties);

        rabbitTemplate.send("topic001", "spring.abc", message);
        rabbitTemplate.send("topic002", "rabbit.abc", message);
    }

    @Test
    public void testSendJsonMessage() throws Exception {

        Order order = new Order();
        order.setId("001");
        order.setName("test1001消息订单");
        order.setContent("test1001订单描述信息");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(order);
        log.info("order 4 json: " + json);

        MessageProperties messageProperties = new MessageProperties();
        //这里注意一定要修改contentType为 application/json
        messageProperties.setContentType("application/json");
        Message message = new Message(json.getBytes(), messageProperties);

        rabbitTemplate.send("topic001", "spring.order", message);
    }

    @Test
    public void testSendJavaMessage() throws Exception {
        Order order = new Order();
        order.setId("1002");
        order.setName("test1002消息订单");
        order.setContent("test1002订单描述信息");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(order);
        log.info("order java: " + json);

        MessageProperties messageProperties = new MessageProperties();
        //这里注意一定要修改contentType为 application/json
        messageProperties.setContentType("application/json");
        //注意这里要写你的实体类路径
        messageProperties.getHeaders().put("__TypeId__", "com.hmily.rabbitmqapi.spring.domain.Order");
        Message message = new Message(json.getBytes(), messageProperties);

        rabbitTemplate.send("topic001", "spring.order", message);
    }

    @Test
    public void testSendMappingMessage() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Order order = new Order();
        order.setId("1001");
        order.setName("1001订单消息");
        order.setContent("1001订单描述信息");
        String json1 = mapper.writeValueAsString(order);
        log.info("order java: " + json1);

        MessageProperties messageProperties1 = new MessageProperties();
        //这里注意一定要修改contentType为 application/json
        messageProperties1.setContentType("application/json");
        messageProperties1.getHeaders().put("__TypeId__", "order");
        Message message1 = new Message(json1.getBytes(), messageProperties1);
        rabbitTemplate.send("topic001", "spring.order", message1);

        Packaged pack = new Packaged();
        pack.setId("1002");
        pack.setName("1002包裹消息");
        pack.setDescription("1002包裹描述信息");
        String json2 = mapper.writeValueAsString(pack);
        log.info("pack  java: " + json2);

        MessageProperties messageProperties2 = new MessageProperties();
        //这里注意一定要修改contentType为 application/json
        messageProperties2.setContentType("application/json");
        messageProperties2.getHeaders().put("__TypeId__", "packaged");
        Message message2 = new Message(json2.getBytes(), messageProperties2);
        rabbitTemplate.send("topic001", "spring.pack", message2);
    }

    @Test
    public void testSendExtConverterMessage() throws Exception {
//        byte[] body = Files.readAllBytes(Paths.get("G:/test/file", "1001.png"));
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setContentType("image/png");
//        messageProperties.getHeaders().put("extName", "png");
//        Message message = new Message(body, messageProperties);
//        rabbitTemplate.send("", "image_queue", message);

        byte[] body = Files.readAllBytes(Paths.get("G:/test/file", "AliTech101_RD.pdf"));
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/pdf");
        Message message = new Message(body, messageProperties);
        rabbitTemplate.send("", "pdf_queue", message);
    }
}
