package com.lee.rocketmq.service.impl;

import com.lee.rocketmq.dto.OperationResult;
import com.lee.rocketmq.dto.OrderPaidEvent;
import com.lee.rocketmq.service.RocketMQService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.apache.rocketmq.spring.starter.core.RocketMQTemplate;

/**
 * @author min
 */
@Service
public class RocketMQServiceImpl implements RocketMQService {

  @Autowired
  private RocketMQTemplate rocketMQTemplate;

  /**
   * 发送消息
   */
  @Override
  public OperationResult<Boolean> sendMessage() {

    rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
    rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
    rocketMQTemplate.convertAndSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")));

    return new OperationResult<>(true);
  }
}