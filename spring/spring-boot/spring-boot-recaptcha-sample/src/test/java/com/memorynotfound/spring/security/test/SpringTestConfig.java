package com.memorynotfound.spring.security.test;

import static org.mockito.Mockito.mock;

import com.memorynotfound.spring.security.recaptcha.ReCaptchaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringTestConfig {

  @Bean
  ReCaptchaService reCaptchaService() {
    return mock(ReCaptchaService.class);
  }

}
