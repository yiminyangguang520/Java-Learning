package com.hellokoding.sso.resource;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author litz-a
 */
@SpringBootApplication
public class WebApplication {

  @Value("${services.auth}")
  private String authService;

  @Bean
  public FilterRegistrationBean jwtFilter() {
    final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    registrationBean.setFilter(new JwtFilter());
    registrationBean.setInitParameters(Collections.singletonMap("services.auth", authService));
    registrationBean.addUrlPatterns("/protected-resource", "/logout");

    return registrationBean;
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(WebApplication.class, args);
  }
}

