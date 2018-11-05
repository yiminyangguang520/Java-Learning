package com.demo.rabbitmq.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author litz-a
 */
@Configuration
public class QueueConfig {

  public final static String TX_EVENT_QUEUE = "tx_event_queue";

  @Bean
  Queue queue() {
    return new Queue(TX_EVENT_QUEUE, false);
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange("spring-boot-rabbitmq-exchange");
  }

  @Bean
  Binding binding(Queue queue,
      TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(TX_EVENT_QUEUE);
  }
}
