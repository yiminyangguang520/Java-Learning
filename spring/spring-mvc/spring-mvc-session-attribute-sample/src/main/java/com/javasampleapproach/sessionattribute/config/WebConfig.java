package com.javasampleapproach.sessionattribute.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author min
 */
@Configuration
@ComponentScan("com.javasampleapproach.sessionattribute")
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  CounterInterceptor counterInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(counterInterceptor);
  }

}
