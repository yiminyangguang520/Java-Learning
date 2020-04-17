package com.javasampleapproach.requestattribute.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author min
 */
@Configuration
@ComponentScan("com.javasampleapproach.requestattribute")
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  CounterInterceptor counterInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(counterInterceptor);
  }

}
