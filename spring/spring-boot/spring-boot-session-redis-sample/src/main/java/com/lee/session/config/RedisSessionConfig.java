package com.lee.session.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author min
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800, redisNamespace = "lee")
public class RedisSessionConfig implements BeanClassLoaderAware {

  private ClassLoader loader;

  @Bean("springSessionDefaultRedisSerializer")
  public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
//    return new GenericJackson2JsonRedisSerializer();
    // 如果采用上述方法,则程序无法启动
    return new GenericJackson2JsonRedisSerializer(objectMapper());
  }

  /**
   * Customized {@link ObjectMapper} to add mix-in for class that doesn't have default
   * constructors
   *
   * @return the {@link ObjectMapper} to use
   */
  private ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModules(SecurityJackson2Modules.getModules(this.loader));
    return mapper;
  }

  @Override
  public void setBeanClassLoader(ClassLoader classLoader) {
    this.loader = classLoader;
  }
}