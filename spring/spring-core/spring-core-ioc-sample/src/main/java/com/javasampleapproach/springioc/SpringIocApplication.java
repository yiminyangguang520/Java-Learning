package com.javasampleapproach.springioc;

import com.javasampleapproach.springioc.service.CustomerService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litz-a
 */
@SpringBootApplication
public class SpringIocApplication {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
    // FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("E:/STS/WorkPlace/SpringIoC/src/main/resources/bean.xml");

    CustomerService service = context.getBean(CustomerService.class);

    System.out.println(service.callSupport());

    context.close();
  }
}
