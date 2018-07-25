package com.xncoding.trans.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
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


  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private int port;

  @Value("${spring.redis.lettuce.pool.min-idle}")
  private int minIdle;

  @Value("${spring.redis.lettuce.pool.max-idle}")
  private int maxIdle;

  @Bean
  public RedisStandaloneConfiguration redisStandaloneConfiguration() {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(host, port);
    return configuration;
  }

  @Bean("customLettuceConnectionFactory")
  public LettuceConnectionFactory connectionFactory(RedisStandaloneConfiguration redisStandaloneConfiguration) {
    // 连接池
    GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
    poolConfig.setMinIdle(minIdle);
    poolConfig.setMaxIdle(maxIdle);

    // 客户端配置
    LettuceClientConfiguration lettuceClientConfiguration = LettucePoolingClientConfiguration
        .builder()
        .poolConfig(poolConfig)
        .build();

    return new LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration);
  }

  /**
   * 方式3 重新配置RedisCacheManager
   */
  @Bean
  public CacheManager cacheManager(@Qualifier("customLettuceConnectionFactory") LettuceConnectionFactory lettuceConnectionFactory) {
    RedisCacheWriter cacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(lettuceConnectionFactory);
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
  @Bean("createCacheManager")
  public CacheManager createCacheManager(RedisConnectionFactory connectionFactory) {
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
  @Bean("builderCacheManager")
  public CacheManager builderCacheManager(RedisConnectionFactory redisConnectionFactory) {
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
