package com.lee.rocketmq.consumer;

import com.lee.rocketmq.dto.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.starter.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.starter.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
public class OrderConsumer implements RocketMQListener<OrderPaidEvent> {

  @Override
  public void onMessage(OrderPaidEvent orderPaidEvent) {
    System.out.println(orderPaidEvent.toString());
  }
}
