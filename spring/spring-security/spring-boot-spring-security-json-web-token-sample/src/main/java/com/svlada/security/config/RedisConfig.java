package com.svlada.security.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author min
 */
@EnableRedisHttpSession
public class RedisConfig {

  @Bean
  public RedisTemplate<Object, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(lettuceConnectionFactory);

    redisTemplate.setValueSerializer(buildJackson2JsonRedisSerializer());
    redisTemplate.setKeySerializer(new StringRedisSerializer());

    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  private GenericJackson2JsonRedisSerializer buildJackson2JsonRedisSerializer() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    // GenericJackson2JsonRedisSerializer 替换默认序列化,Jackson序列化器其实有两个,Jackson2JsonRedisSerializer和我们上面使用的GenericJackson2JsonRedisSerializer,
    // 如果使用Jackson2JsonRedisSerializer在反序列化时会遇到问题,因为没有具体泛型或泛型为Object时,会将缓存中的数据反序列化为LinkedHashMap
    return new GenericJackson2JsonRedisSerializer(objectMapper);
  }
}
