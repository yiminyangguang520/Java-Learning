package com.waylau.spring.boot.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring Security 配置类.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2017年3月8日
 */
@Configuration
@EnableWebMvc
public class SecurityConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    // 允许跨域请求
    registry.addMapping("/**").allowedOrigins("*");
  }
}