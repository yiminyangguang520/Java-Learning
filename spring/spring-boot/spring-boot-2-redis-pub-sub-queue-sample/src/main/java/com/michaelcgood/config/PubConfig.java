package com.michaelcgood.config;

import com.michaelcgood.queue.publish.MessagePublisher;
import com.michaelcgood.queue.publish.impl.MessagePublisherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

/**
 * @author min
 */
@Configuration
public class PubConfig {

  @Autowired
  private RedisTemplate redisTemplate;

  @Bean
  MessagePublisher redisPublisher() {
    return new MessagePublisherImpl(redisTemplate, topic());
  }

  @Bean
  ChannelTopic topic() {
    return new ChannelTopic("pubsub:queue");
  }
}
