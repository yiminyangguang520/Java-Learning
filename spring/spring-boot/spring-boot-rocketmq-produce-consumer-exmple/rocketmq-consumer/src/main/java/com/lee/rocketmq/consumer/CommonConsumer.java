package com.lee.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.spring.starter.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.starter.core.RocketMQListener;
import org.apache.rocketmq.spring.starter.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
public class CommonConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

  @Override
  public void onMessage(String message) throws Exception {
    System.out.println(message);
    throw new Exception("测试");
  }

  /**
   * 指定消费者从哪开始消费消息，或开始消费的位置
   */
  @Override
  public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
    // set consumer consume message from now
    defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
    defaultMQPushConsumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis()));
    defaultMQPushConsumer.setMaxReconsumeTimes(3);
  }


}
