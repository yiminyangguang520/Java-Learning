package com.javasampleapproach.redis.pubsub.config;

import com.javasampleapproach.redis.pubsub.consumer.CustomerInfoSubscriber;
import com.javasampleapproach.redis.pubsub.producer.CustomerInfoPublisher;
import com.javasampleapproach.redis.pubsub.producer.RedisCustomerInfoPublisher;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

/**
 * @author min
 */
@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    final RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
    return template;
  }

  @Bean
  public MessageListenerAdapter messageListener() {
    return new MessageListenerAdapter(new CustomerInfoSubscriber());
  }

  @Bean
  public RedisMessageListenerContainer redisContainer(RedisConnectionFactory redisConnectionFactory) {
    final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(redisConnectionFactory);
    container.addMessageListener(messageListener(), topic());
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4,
        0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    container.setTaskExecutor(threadPoolExecutor);
    return container;
  }

  @Bean
  public CustomerInfoPublisher redisPublisher(RedisTemplate<String, Object> redisTemplate) {
    return new RedisCustomerInfoPublisher(redisTemplate, topic());
  }

  @Bean
  public ChannelTopic topic() {
    return new ChannelTopic("pubsub:jsa-channel");
  }
}
