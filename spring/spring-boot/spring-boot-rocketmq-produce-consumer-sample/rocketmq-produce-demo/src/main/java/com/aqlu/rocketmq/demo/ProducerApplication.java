package com.aqlu.rocketmq.demo;

import com.aqlu.rocketmq.demo.domain.OrderPaidEvent;
import java.math.BigDecimal;
import org.apache.rocketmq.spring.starter.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author aqlu
 * @date 2017/11/16
 */
@SpringBootApplication
public class ProducerApplication implements CommandLineRunner {

  @Autowired
  private RocketMQTemplate rocketMQTemplate;

  public static void main(String[] args) {
    SpringApplication.run(ProducerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    // send string
    rocketMQTemplate.convertAndSend("string-topic", "Hello, World!");

    // send string with spring Message
    rocketMQTemplate.send("string-topic", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());

    // send user-defined object
    rocketMQTemplate.convertAndSend("order-paid-topic", new OrderPaidEvent("T_001", new BigDecimal("88.00")));

    // send message with special tag
    rocketMQTemplate.convertAndSend("message-ext-topic:tag0", "I'm from tag0");
    rocketMQTemplate.convertAndSend("message-ext-topic:tag1", "I'm from tag1");
  }

}
