package com.javasampleapproach.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Component
public class Consumer {

  @RabbitListener(queues = "${jsa.rabbitmq.queue}")
  public void recievedMessage(String msg) {
    System.out.println("Recieved Message: " + msg);
  }
}
