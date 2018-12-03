package com.lee.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.starter.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.starter.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "test-topic-3", consumerGroup = "my-consumer_test-topic-3")
public class ExtConsumer implements RocketMQListener<MessageExt> {

  /**
   * 除了获取消息payload外，还想获取RocketMQ消息的其它系统属性,
   * 消费者在实现RocketMQListener接口时，只需要起泛型为MessageExt即可
   * @param messageExt
   */
  @Override
  public void onMessage(MessageExt messageExt) {
    log.info("received messageExt: {}", messageExt);
  }
}
