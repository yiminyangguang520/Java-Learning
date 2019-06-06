package com.hmily.apigateway.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


/**
 * @author litz-a
 */
@Configuration
public class CorsConfig {

  @Bean
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();

    config.setAllowCredentials(true);
    //http:www.a.com, http:www.b.com
    config.setAllowedOrigins(Collections.singletonList("*"));
    //设置传递的请求头
    config.setAllowedHeaders(Collections.singletonList("*"));
    //设置请求的方法，get、post
    config.setAllowedMethods(Collections.singletonList("*"));
    //设置在这个时间段内不再检查跨域
    config.setMaxAge(300L);
    //针对所有请求检查跨域
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}
