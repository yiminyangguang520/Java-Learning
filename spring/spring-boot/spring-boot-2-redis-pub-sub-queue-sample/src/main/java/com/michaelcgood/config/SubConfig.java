package com.michaelcgood.config;

import com.michaelcgood.queue.subscribe.MessageSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author min
 */
@Configuration
public class SubConfig {

  @Autowired
  private ChannelTopic channelTopic;

  @Autowired
  private JedisConnectionFactory jedisConnectionFactory;

  @Bean
  MessageListenerAdapter messageListener() {
    return new MessageListenerAdapter(new MessageSubscriber());
  }

  @Bean
  RedisMessageListenerContainer redisContainer() {
    final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(jedisConnectionFactory);
    container.addMessageListener(messageListener(), channelTopic);
    return container;
  }
}
