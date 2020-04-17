package com.javasampleapproach.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
public class Consumer {

  @RabbitListener(queues = "${jsa.rabbitmq.queue}")
  public void recievedMessage(String msg) {
    System.out.println("Recieved Message: " + msg);
  }
}
