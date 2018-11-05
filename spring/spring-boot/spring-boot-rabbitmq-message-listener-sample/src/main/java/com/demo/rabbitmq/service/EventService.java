package com.demo.rabbitmq.service;

/**
 * @author litz-a
 */
public interface EventService {

  /**
   * sendEvent
   * @param t
   * @param <T>
   * @return
   */
  <T> Boolean sendEvent(T t);

}
