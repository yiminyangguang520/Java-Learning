package com.lee.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 存在自定义SessionRepository时,redisNamespace命名空间无效,SessionRepository优先级高,需在SessionRepository设置
 * @author min
 */
@Configuration
@EnableRedisHttpSession(redisNamespace = "spring:lee")
public class HttpSessionConfig {

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private int port;

  @Value("${spring.redis.password}")
  private String password;

  @Value("${spring.redis.database}")
  private Integer database;

  /**
   * RedisHttpSession 创建 连接工厂
   *
   * @return LettuceConnectionFactory
   */
  @Bean
  public LettuceConnectionFactory connectionFactory() {
    RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
    config.setHostName(host);
    config.setPort(port);
    config.setPassword(RedisPassword.of(password));
    config.setDatabase(database);

    GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
    poolConfig.setMaxTotal(8);

    LettucePoolingClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder()
        .poolConfig(poolConfig)
        .build();

    return new LettuceConnectionFactory(config, clientConfiguration);
  }
}
