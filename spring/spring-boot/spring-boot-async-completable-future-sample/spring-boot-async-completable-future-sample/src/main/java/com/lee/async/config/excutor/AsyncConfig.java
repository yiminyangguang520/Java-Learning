package com.lee.async.config.excutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author bruce
 * @date 2019/10/22
 * @description
 */
@Configuration
@EnableAsync
public class AsyncConfig {

  @Autowired
  private ExcutorProperties excutorProperties;

  @Bean
  public Executor taskExecutor() {
    // Spring 默认配置是核心线程数大小为1，最大线程容量大小不受限制，队列容量也不受限制。
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    // 核心线程数
    executor.setCorePoolSize(excutorProperties.getCorePoolSize());
    // 最大线程数
    executor.setMaxPoolSize(excutorProperties.getMaxPoolSize());
    // 队列大小
    executor.setQueueCapacity(excutorProperties.getMaxPoolSize());
    // 当最大池已满时，此策略保证不会丢失任务请求，但是可能会影响应用程序整体性能。
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.setThreadNamePrefix("executor-thread-");
    executor.initialize();
    return executor;
  }
}
