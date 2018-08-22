package com.lee.errorhandle.config;

import com.lee.errorhandle.handler.InterceptorWithExceptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author litz-a
 */
@Configuration
public class CustomWebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new InterceptorWithExceptions());
  }
}
