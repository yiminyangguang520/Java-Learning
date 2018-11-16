package com.lee.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.starter.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.starter.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
public class CommonConsumer implements RocketMQListener<String> {

  @Override
  public void onMessage(String message) {
    System.out.println(message);
  }
}
