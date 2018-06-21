package com.javasampleapproach.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Component
public class Producer {

  @Autowired
  private AmqpTemplate amqpTemplate;

  @Value("${jsa.rabbitmq.exchange}")
  private String exchange;

  @Value("${jsa.rabbitmq.routingkey}")
  private String routingKey;

  public void produceMsg(String msg) {
    amqpTemplate.convertAndSend(exchange, routingKey, msg);
    System.out.println("Send msg = " + msg);
  }
}

