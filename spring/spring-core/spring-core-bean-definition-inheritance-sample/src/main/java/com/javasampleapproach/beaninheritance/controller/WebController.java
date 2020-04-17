package com.javasampleapproach.beaninheritance.controller;

import com.javasampleapproach.beaninheritance.service.CustomerService;
import com.javasampleapproach.beaninheritance.service.SpecificService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class WebController {

  ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

  CustomerService service = (CustomerService) context.getBean("customerService");
  CustomerService anotherService = (CustomerService) context.getBean("anotherCustomerService");
  SpecificService telephoneService = (SpecificService) context.getBean("telephoneService");
  SpecificService consulteService = (SpecificService) context.getBean("consultationService");

  @RequestMapping("/service")
  public String generalService() {
    return service.sayHello();
  }

  @RequestMapping("/anotherservice")
  public String anotherService() {
    return anotherService.sayHello();
  }

  @RequestMapping("/telephoneservice")
  public String telephoneService() {
    return telephoneService.sayHello();
  }

  @RequestMapping("/consultservice")
  public String consultationService() {
    return consulteService.sayWelcome();
  }
}
