package com.us.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author min
 */
@Configuration
public class Encoders {

  /**
   * spring 5必须指定一种加密方式,否则会报错:There is no PasswordEncoder mapped for the id “null”
   * @return
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}