package com.mkyong.config;

import com.mkyong.core.SchedulerBo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author litz-a
 */
@Configuration
public class SchedulerConfig {

  @Bean(name = "scheduler")
  public SchedulerBo suchedulerBo() {

    return new SchedulerBo();

  }

}