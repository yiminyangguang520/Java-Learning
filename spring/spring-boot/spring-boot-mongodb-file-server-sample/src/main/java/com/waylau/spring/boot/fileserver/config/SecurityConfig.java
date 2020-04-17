package com.waylau.spring.boot.fileserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author min
 */
@Configuration
@EnableWebMvc
public class SecurityConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    // 允许跨域请求
    registry.addMapping("/**").allowedOrigins("*");
  }
}