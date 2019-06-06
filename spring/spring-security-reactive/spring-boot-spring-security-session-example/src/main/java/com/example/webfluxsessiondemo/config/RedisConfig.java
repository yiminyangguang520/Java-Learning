package com.example.webfluxsessiondemo.config;

import io.lettuce.core.resource.DefaultClientResources;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * @author wangxing
 * @create 2019/5/16
 */
@Configuration
public class RedisConfig {

  @Bean
  public BeanPostProcessor lettuceConnectionFactoryObjectPostProcessor() {
    return new BeanPostProcessor() {
      @Override
      public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof LettuceConnectionFactory) {
          LettuceConnectionFactory lettuceConnectionFactory = ((LettuceConnectionFactory) bean);
          lettuceConnectionFactory.setShareNativeConnection(false);
        }
        return bean;
      }
    };
  }

  @Bean(destroyMethod = "shutdown")
  public DefaultClientResources lettuceClientResources() {
    return DefaultClientResources.builder()
        .ioThreadPoolSize(20)
        .computationThreadPoolSize(20)
        .build();
  }

}