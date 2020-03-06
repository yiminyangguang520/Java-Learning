package com.techshard.future.config;

import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author bruce
 */
@EnableAsync
@Configuration
public class AsyncConfiguration {

  private static final Logger LOGGER = LoggerFactory.getLogger(AsyncConfiguration.class);

  @Bean(name = "taskExecutor")
  public Executor taskExecutor() {
    LOGGER.debug("Creating Async Task Executor");
    final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    LOGGER.info("core pool size is " + Runtime.getRuntime().availableProcessors());
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(2);
    executor.setQueueCapacity(25);
    executor.setThreadNamePrefix("CarThread-");
    executor.initialize();
    return executor;
  }

}

