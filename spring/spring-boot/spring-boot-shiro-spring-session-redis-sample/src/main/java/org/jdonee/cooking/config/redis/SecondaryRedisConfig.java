package org.jdonee.cooking.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Method;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis服务器对象缓存配置(对象缓存和Session缓存)
 *
 * @author Frank.Zeng
 */
@Configuration
@EnableCaching
@EnableRedisHttpSession
@Slf4j
public class SecondaryRedisConfig extends CachingConfigurerSupport {

  /**
   * 加载属性文件数据
   */
  @Bean
  public CustomRedisProperties redisProperties() {
    return new CustomRedisProperties();
  }

  /**
   * 主键生成器
   */
  @Bean
  public KeyGenerator commonKeyGenerator() {
    return (Object target, Method method, Object... params) -> {
        StringBuilder sb = new StringBuilder();
        sb.append(target.getClass().getName());
        sb.append(method.getName());
        for (Object obj : params) {
          sb.append(obj.toString());
        }
        String key = sb.toString();
        log.info("key:" + key);
        return key;
      };
  }

  @Bean
  public CacheManager cacheManager(@Qualifier("jedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory) {
    RedisCacheManager redisCacheManager = RedisCacheManager.create(jedisConnectionFactory);
    return redisCacheManager;
  }

  @Bean
  public JedisPoolConfig jedisPoolConfig() {
    JedisPoolConfig config = new JedisPoolConfig();
    CustomRedisProperties.Pool props = redisProperties().getJedis().getPool();
    config.setMaxTotal(props.getMaxActive());
    config.setMaxIdle(props.getMaxIdle());
    config.setMinIdle(props.getMinIdle());
    config.setMaxWaitMillis(props.getMaxWait());
    return config;
  }

  @Bean
  public RedisStandaloneConfiguration getRedisStandaloneConfiguration(CustomRedisProperties redisProperties) {
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setHostName(redisProperties.getHost());
    redisStandaloneConfiguration.setPort(redisProperties.getPort());
    redisStandaloneConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
    redisStandaloneConfiguration.setDatabase(redisProperties.getSecondaryDatabase());
    return redisStandaloneConfiguration;
  }

  @Bean
  public JedisClientConfiguration getJedisClientConfiguration(JedisPoolConfig jedisPoolConfig,
      CustomRedisProperties redisProperties) {
    JedisClientConfiguration.JedisPoolingClientConfigurationBuilder JedisPoolingClientConfigurationBuilder = (
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
    return JedisPoolingClientConfigurationBuilder.poolConfig(jedisPoolConfig)
        .and()
        .connectTimeout(Duration.ofSeconds(redisProperties.getTimeout()))
        .readTimeout(Duration.ofSeconds(redisProperties.getTimeout()))
        .build();
  }

  @Bean(name="jedisConnectionFactory")
  public JedisConnectionFactory secondaryRedisConnectionFactory(RedisStandaloneConfiguration redisStandaloneConfiguration,
      JedisClientConfiguration jedisClientConfiguration) {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration,
        jedisClientConfiguration);

    jedisConnectionFactory.afterPropertiesSet();
    log.info("2.1 初始化Redis缓存服务器(普通对象)... ...");
    return jedisConnectionFactory;
  }

  @Bean(name="secondaryStringRedisTemplate")
  public RedisTemplate<String, String> redisTemplate(
      @Qualifier("jedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory) {
    log.info("2.2 初始化Redis模板(普通对象)... ...");
    StringRedisTemplate template = new StringRedisTemplate(jedisConnectionFactory);
    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    // 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper
    om.setSerializationInclusion(Include.NON_EMPTY);
    GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(om);
    template.setValueSerializer(jackson2JsonRedisSerializer);
    // template.afterPropertiesSet();
    return template;
  }

  /**
   * 与Session有关设置链接
   */
  @Bean
  public RedisOperationsSessionRepository sessionRepository(CustomRedisProperties redisProperties,
      @Qualifier("redisTemplate") RedisTemplate redisTemplate) {
    RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(
        redisTemplate);
    // 设置session的有效时长
    sessionRepository.setDefaultMaxInactiveInterval(redisProperties.getSessionExpire());
    return sessionRepository;
  }

  /**
   * RedisTemplate
   */
  @Bean(name = "redisTemplate")
  public RedisTemplate<String, Object> sessionRedisTemplate(
      @Qualifier("jedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory);
    RedisSerializer stringSerializer = new StringRedisSerializer();
    template.setKeySerializer(stringSerializer);
    template.setValueSerializer(sessionRedisSerializer());
    template.setHashKeySerializer(stringSerializer);
    template.setHashValueSerializer(sessionRedisSerializer());
    template.afterPropertiesSet();
    return template;
  }

  /**
   * 设置redisTemplate的存储格式（在此与Session没有什么关系）
   */
  @Bean
  public RedisSerializer sessionRedisSerializer() {
    return new Jackson2JsonRedisSerializer<>(Object.class);
  }
}
