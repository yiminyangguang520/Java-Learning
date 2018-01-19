package com.mkyong.config;

import com.mkyong.hello.HelloWorld;
import com.mkyong.hello.impl.HelloWorldImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author litz-a
 */
@Configuration
public class AppConfig {

  @Bean(name = "helloBean")
  public HelloWorld helloWorld() {
    return new HelloWorldImpl();
  }

}