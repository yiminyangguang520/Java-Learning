package com.xncoding.trans.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * RedisCacheConfig
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/2
 */
@Configuration
@EnableCaching
public class RedisCacheConfig {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * key serializer
   */
  public final StringRedisSerializer STRING_SERIALIZER = new StringRedisSerializer();

  /**
   * value serializer
   * <pre>
   *     使用 FastJsonRedisSerializer 会报错：java.lang.ClassCastException
   *     FastJsonRedisSerializer<Object> fastSerializer = new FastJsonRedisSerializer<>(Object.class);
   * </pre>
   */
  public final GenericFastJsonRedisSerializer FASTJSON_SERIALIZER = new GenericFastJsonRedisSerializer();

  /**
   * key serializer pair
   */
  public final RedisSerializationContext.SerializationPair<String> STRING_PAIR = RedisSerializationContext
      .SerializationPair.fromSerializer(STRING_SERIALIZER);
  /**
   * value serializer pair
   */
  public final RedisSerializationContext.SerializationPair<Object> FASTJSON_PAIR = RedisSerializationContext
      .SerializationPair.fromSerializer(FASTJSON_SERIALIZER);

  /**
   * 重新配置RedisCacheManager
   * @param connectionFactory
   * @return
   */
  @Bean
  public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
    RedisCacheWriter cacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
    // 设置默认过期时间：30 分钟
    RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofMinutes(30))
//        .disableCachingNullValues()
        // 使用注解时的序列化、反序列化
        .serializeKeysWith(STRING_PAIR)
        .serializeValuesWith(FASTJSON_PAIR);

    return new RedisCacheManager(cacheWriter, defaultCacheConfig);
  }

  /**
   * 方式1
   * @param connectionFactory
   * @return
   */
  /*
  @Bean
  public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
    RedisCacheManager redisCacheManager = RedisCacheManager.create(connectionFactory);
    return redisCacheManager;
  }
  */

  /**
   * 方式2
   * @param redisConnectionFactory
   * @return
   */
  /*
  @Bean
  public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
    RedisCacheManager.create(redisConnectionFactory);
    RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
        .RedisCacheManagerBuilder
        .fromConnectionFactory(redisConnectionFactory);
    return builder.build();
  }
  */



  /**
   * 自定义缓存key的生成类实现
   */
  @Bean(name = "myKeyGenerator")
  public KeyGenerator myKeyGenerator() {
    return (Object o, Method method, Object... params) -> {
      logger.info("自定义缓存，使用第一参数作为缓存key，params = " + Arrays.toString(params));
      // 仅仅用于测试，实际不可能这么写
      return params[0];
    };
  }
}
