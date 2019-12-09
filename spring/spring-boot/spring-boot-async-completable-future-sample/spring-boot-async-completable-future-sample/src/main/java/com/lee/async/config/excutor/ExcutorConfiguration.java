package com.lee.async.config.excutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bruce
 * @date 2019/10/22
 * @description 配置类注入容器
 */
@Configuration
public class ExcutorConfiguration {

  @Bean
  public ExcutorProperties excutorProperties() {
    return new ExcutorProperties();
  }
}
