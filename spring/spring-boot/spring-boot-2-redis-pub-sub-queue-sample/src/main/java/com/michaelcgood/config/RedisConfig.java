package com.michaelcgood.config;

import com.michaelcgood.queue.publish.MessagePublisher;
//import com.michaelcgood.queue.publish.impl.MessagePublisherImpl;
//import com.michaelcgood.queue.subscribe.MessageSubscriber;
import com.michaelcgood.queue.publish.impl.MessagePublisherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author min
 */
@Configuration
@ComponentScan("com.michaelcgood")
public class RedisConfig {

  @Autowired
  private JedisConnectionFactory jedisConnectionFactory;

  @Bean
  public JedisClientConfiguration getJedisClientConfiguration() {
    JedisClientConfiguration.JedisPoolingClientConfigurationBuilder JedisPoolingClientConfigurationBuilder = (
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    return JedisPoolingClientConfigurationBuilder.poolConfig(jedisPoolConfig).build();
  }

  @Bean
  JedisConnectionFactory jedisConnectionFactory(@Value("${spring.redis.host}") String host,
      @Value("${spring.redis.port}") int port, JedisClientConfiguration jedisClientConfiguration) {
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setHostName(host);
    redisStandaloneConfiguration.setPort(port);
    redisStandaloneConfiguration.setPassword(RedisPassword.none());

    JedisClientConfiguration defaultJedisClientConfiguration = JedisClientConfiguration.defaultConfiguration();
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    return jedisConnectionFactory;
  }

//  @Bean
//  JedisConnectionFactory jedisConnectionFactory() {
//    return new JedisConnectionFactory();
//  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    final RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory);
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
    template.setHashKeySerializer(new StringRedisSerializer());
    template.setHashValueSerializer(new StringRedisSerializer());
    return template;
  }
}