package com.michaelcgood.queue.publish;

/**
 * @author min
 */
public interface MessagePublisher {

  /**
   * 发布消息
   * @param message
   */
  void publish(final String message);
}
