package com.lee.config;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * 设置队列消息的过期时间（针对每个消息）
 * @author min
 */
public class ExpirationMessagePostProcessor implements MessagePostProcessor {

  /**
   * 毫秒
   */
  private final Long ttl;

  public ExpirationMessagePostProcessor(Long ttl) {
    this.ttl = ttl;
  }

  @Override
  public Message postProcessMessage(Message message) throws AmqpException {
    message.getMessageProperties()
        // 设置per-message的失效时间
        .setExpiration(ttl.toString());
    return message;
  }
}
