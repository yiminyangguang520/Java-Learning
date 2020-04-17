package com.javasampleapproach.rabbitmq.consumer;

import com.javasampleapproach.rabbitmq.model.Company;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
public class Consumer {

  @RabbitListener(queues = "${jsa.rabbitmq.queue}", containerFactory = "jsaFactory")
  public void recievedMessage(Company company) {
    System.out.println("Recieved Message: " + company);
  }
}