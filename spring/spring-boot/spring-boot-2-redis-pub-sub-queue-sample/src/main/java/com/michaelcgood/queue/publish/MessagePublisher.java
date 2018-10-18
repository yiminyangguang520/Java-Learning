package com.michaelcgood.queue.publish;

/**
 * @author litz-a
 */
public interface MessagePublisher {

  /**
   * 发布消息
   * @param message
   */
  void publish(final String message);
}
