package com.javasampleapproach.rabbitmq.producer;

import com.javasampleapproach.rabbitmq.model.Company;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
public class Producer {

  @Autowired
  private AmqpTemplate amqpTemplate;

  @Value("${jsa.rabbitmq.exchange}")
  private String exchange;

  @Value("${jsa.rabbitmq.routingkey}")
  private String routingkey;

  public void produce(Company company) {
    amqpTemplate.convertAndSend(exchange, routingkey, company);
    System.out.println("Send msg = " + company);
  }
}