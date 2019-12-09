package com.lee.async.config.excutor;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author bruce
 * @date 2019/10/21
 * @description 线程池配置实体
 */
@Data
@ConfigurationProperties(prefix = "excutor")
public class ExcutorProperties {

  private int corePoolSize = 5;

  private int maxPoolSize = 10;

  private int queueCapacity = 64;
}
