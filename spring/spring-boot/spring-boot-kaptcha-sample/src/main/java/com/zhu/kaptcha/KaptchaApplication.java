package com.zhu.kaptcha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author litz-a
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:kaptcha/kaptcha.xml"})
public class KaptchaApplication {

  public static void main(String[] args) {
    SpringApplication.run(KaptchaApplication.class, args);
  }
}
