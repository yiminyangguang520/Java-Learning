package com.memorynotfound;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author litz-a
 */
public class MessageProducer {

  public static void main(String... args) throws InterruptedException {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    RedisService redisService = ctx.getBean(RedisService.class);
    redisService.setValue("key", "hello world!");

    System.out.println("message: " + redisService.getValue("key"));
    Thread.sleep(1000 * 6);
    System.out.println("message: " + redisService.getValue("key"));
  }
}
