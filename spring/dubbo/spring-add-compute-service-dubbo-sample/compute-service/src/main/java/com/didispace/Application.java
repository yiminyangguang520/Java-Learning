package com.didispace;

import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

/**
 * @author min
 */
@Slf4j
@SpringBootApplication
@ImportResource({"classpath:dubbo.xml"})
public class Application {

  public static void main(String[] args) throws InterruptedException {
    ApplicationContext ctx = SpringApplication.run(Application.class, args);
    log.info("项目启动!");
    CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
    closeLatch.await();
  }

  @Bean
  public CountDownLatch closeLatch() {
    return new CountDownLatch(1);
  }

}
