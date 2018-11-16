package com.wolf.mongodbit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 定义配置类 定义字符串转时间的转换格式，使字符串自动转换为时间。注册到注册中心。
 * @author litz-a
 */
@Configuration
public class StringToDateConfig implements WebMvcConfigurer {

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm"));
    registry.addFormatter(new DateFormatter("yyyy-MM-dd HH"));
    registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
  }
}
