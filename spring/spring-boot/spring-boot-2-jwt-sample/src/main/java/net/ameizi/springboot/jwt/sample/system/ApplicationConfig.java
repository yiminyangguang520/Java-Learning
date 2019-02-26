package net.ameizi.springboot.jwt.sample.system;

import java.util.List;
import net.ameizi.springboot.jwt.sample.system.config.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {

  @Autowired
  private JwtFilter jwtFilter;

  @Bean
  public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
    return new DeviceResolverHandlerInterceptor();
  }

  @Bean
  public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() {
    return new DeviceHandlerMethodArgumentResolver();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(deviceResolverHandlerInterceptor());
    registry.addInterceptor(jwtFilter)
        .addPathPatterns("/**")
        .excludePathPatterns("/**/register", "/**/token", "/**/refresh-token");
  }


  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(deviceHandlerMethodArgumentResolver());
  }

}