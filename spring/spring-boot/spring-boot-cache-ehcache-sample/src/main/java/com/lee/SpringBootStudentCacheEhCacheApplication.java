package com.lee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author litz-a
 * @EnableCaching// 开启缓存，需要显示的指定
 */
@SpringBootApplication
@EnableCaching
public class SpringBootStudentCacheEhCacheApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootStudentCacheEhCacheApplication.class, args);
  }
}
