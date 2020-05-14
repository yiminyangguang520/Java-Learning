package com.lee.example.webflux.config;

import com.lee.example.webflux.domain.Coffee;
import com.lee.example.webflux.domain.EnhanceAccessToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author lee
 * @date 2020-03-27
 */
@Configuration
public class CoffeeConfiguration {

  @Bean
  public ReactiveRedisOperations<String, Coffee> redisOperations(ReactiveRedisConnectionFactory factory) {
    Jackson2JsonRedisSerializer<Coffee> serializer = new Jackson2JsonRedisSerializer<>(Coffee.class);

    RedisSerializationContext.RedisSerializationContextBuilder<String, Coffee> builder =
        RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

    RedisSerializationContext<String, Coffee> context = builder.value(serializer).build();

    return new ReactiveRedisTemplate<>(factory, context);
  }

  @Bean
  public ReactiveRedisOperations<String, EnhanceAccessToken> redisTokenOperations(ReactiveRedisConnectionFactory factory) {
    Jackson2JsonRedisSerializer<EnhanceAccessToken> serializer = new Jackson2JsonRedisSerializer<>(EnhanceAccessToken.class);

    RedisSerializationContext.RedisSerializationContextBuilder<String, EnhanceAccessToken> builder =
        RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

    RedisSerializationContext<String, EnhanceAccessToken> context = builder.value(serializer).build();

    return new ReactiveRedisTemplate<>(factory, context);
  }
}
