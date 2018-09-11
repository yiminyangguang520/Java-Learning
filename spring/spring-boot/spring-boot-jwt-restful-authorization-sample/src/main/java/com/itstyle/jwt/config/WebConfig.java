package com.itstyle.jwt.config;

import com.itstyle.jwt.interceptor.SysInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截配置--调用链
 * @author litz-a
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    String[] patterns = new String[]{"/login"};
    registry.addInterceptor(new SysInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns(patterns);
  }

}