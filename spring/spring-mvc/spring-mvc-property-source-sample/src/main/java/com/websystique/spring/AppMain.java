package com.websystique.spring;

import com.websystique.spring.configuration.AppConfig;
import com.websystique.spring.service.FileService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * @author litz-a
 */
public class AppMain {

  public static void main(String[] args) {
    AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    FileService service = (FileService) context.getBean("fileService");

    service.readValues();
    context.close();
  }

}
