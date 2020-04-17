package com.demo.rabbitmq.service;

/**
 * @author min
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
