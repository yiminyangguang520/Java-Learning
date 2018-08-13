package com.scienjus.config;

import com.scienjus.authorization.interceptor.AuthorizationInterceptor;
import com.scienjus.authorization.resolvers.CurrentUserMethodArgumentResolver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类，增加自定义拦截器和解析器
 *
 * @author ScienJus
 * @date 2015/7/30.
 * @see com.scienjus.authorization.resolvers.CurrentUserMethodArgumentResolver
 * @see com.scienjus.authorization.interceptor.AuthorizationInterceptor
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Autowired
  private AuthorizationInterceptor authorizationInterceptor;

  @Autowired
  private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authorizationInterceptor);
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(currentUserMethodArgumentResolver);
  }
}
