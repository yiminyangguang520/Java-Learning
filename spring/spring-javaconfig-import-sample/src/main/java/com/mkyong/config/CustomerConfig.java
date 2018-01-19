package com.mkyong.config;

import com.mkyong.core.CustomerBo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author litz-a
 */
@Configuration
public class CustomerConfig {

  @Bean(name = "customer")
  public CustomerBo customerBo() {

    return new CustomerBo();

  }
}