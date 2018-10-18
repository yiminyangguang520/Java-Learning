package com.michaelcgood.queue.publish.impl;

import com.michaelcgood.queue.publish.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Service
public class MessagePublisherImpl implements MessagePublisher {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @Autowired
  private ChannelTopic topic;

  public MessagePublisherImpl() {
  }

  public MessagePublisherImpl(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
    this.redisTemplate = redisTemplate;
    this.topic = topic;
  }

  @Override
  public void publish(final String message) {
    System.out.println("Publishing... message" + message + ", " + Thread.currentThread().getName());
    redisTemplate.convertAndSend(topic.getTopic(), message);
  }

}
