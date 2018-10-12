package com.example.configuration;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import java.lang.reflect.Constructor;
import java.time.Duration;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.config.authentication.CachingUserDetailsService;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.util.Assert;

/**
 * @author litz-a
 */
@EnableCaching
@Configuration
public class UserDetailsCacheConfig {

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

  @Autowired
  private org.springframework.cache.CacheManager cacheManager;

  @Autowired
  @Qualifier("userService")
  private UserDetailsService userDetailsService;

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
   * 配置RedisCacheManager
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

  @Bean
  public UserCache userCache() {
    Cache cache = cacheManager.getCache("userDetailsCache");
    try {
      return new SpringCacheBasedUserCache(cache);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Bean
  public UserDetailsService cachingUserDetailsService() {
    Constructor<CachingUserDetailsService> ctor = null;
    try {
      ctor = CachingUserDetailsService.class.getDeclaredConstructor(UserDetailsService.class);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    Assert.notNull(ctor, "CachingUserDetailsService constructor is null");
    ctor.setAccessible(true);

    CachingUserDetailsService cachingUserDetailsService = BeanUtils.instantiateClass(ctor, userDetailsService);
    cachingUserDetailsService.setUserCache(userCache());
    return cachingUserDetailsService;
  }
}
