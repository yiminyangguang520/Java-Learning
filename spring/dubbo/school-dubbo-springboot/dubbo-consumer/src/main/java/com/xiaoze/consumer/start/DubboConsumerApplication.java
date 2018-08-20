package com.xiaoze.consumer.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * DubboConsumerApplication
 *
 * @author xiaoze
 * @date 2018/6/12
 */
@ComponentScan(basePackages = "com.xiaoze.consumer")
@SpringBootApplication
public class DubboConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(DubboConsumerApplication.class, args);
  }
}
