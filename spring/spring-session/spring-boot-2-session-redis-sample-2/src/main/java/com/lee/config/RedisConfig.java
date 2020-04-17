package com.lee.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;

/**
 * @author min
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

  @Bean
  RedisTemplate<String, Object> stringRedisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate redisTemplate = new StringRedisTemplate(connectionFactory);
    // 重新设置 StringRedisTemplate 值的序列化方式，把value内容序列化为json字符串
    // 如果你希望只保存字符串的内容,可以吧下面的内容去掉只用StringRedisTemplate
    Jackson2JsonRedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    redisSerializer.setObjectMapper(om);
    redisTemplate.setValueSerializer(redisSerializer);
    // 应用设置
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  /**
   * 设置 redisTemplate 序列化方式
   */
  @Bean
  public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(connectionFactory);
    // 设置值（value）的序列化采用FastJsonRedisSerializer。
    FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
    redisTemplate.setValueSerializer(fastJsonRedisSerializer);
    redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
    // 设置键（key）的序列化采用StringRedisSerializer。
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  /**
   * 设置spring session redis 序列化方式
   */
  @Bean
  public SessionRepository sessionRepository(RedisConnectionFactory connectionFactory) {
    RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(redisTemplate(connectionFactory));
    FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
    sessionRepository.setDefaultSerializer(fastJsonRedisSerializer);
    sessionRepository.setDefaultMaxInactiveInterval(36000);
    sessionRepository.setRedisKeyNamespace("spring:min");
    return sessionRepository;
  }

  @Bean
  @Override
  public KeyGenerator keyGenerator() {
    return (target, method, params) -> {
      StringBuilder sb = new StringBuilder();
      sb.append(target.getClass().getName());
      sb.append(method.getName());
      for (Object obj : params) {
        sb.append(obj.toString());
      }
      return sb.toString();
    };
  }
}
