package com.lee.rocketmq.service;

import com.lee.rocketmq.dto.OperationResult;

/**
 * @author min
 */
public interface RocketMQService {

  /**
   * 发送消息
   * @return
   */
  OperationResult<Boolean> sendMessage();
}
