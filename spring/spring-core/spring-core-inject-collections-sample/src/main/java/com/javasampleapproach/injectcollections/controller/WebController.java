package com.javasampleapproach.injectcollections.controller;

import com.javasampleapproach.injectcollections.model.Customer;
import com.javasampleapproach.injectcollections.service.CustomerService;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class WebController {

  ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

  CustomerService customerService = (CustomerService) context.getBean("customerService");

  @RequestMapping("/set")
  public Set<String> getSet() {
    return customerService.getServiceSet();
  }

  @RequestMapping("/map")
  public Map<Integer, String> getMap() {
    return customerService.getChannelServiceMap();
  }

  @RequestMapping("/list")
  public List<Customer> getList() {
    return customerService.getCustomerList();
  }

  @RequestMapping("/prop")
  public String getProp() {
    Properties props = customerService.getLanguage();
    return props.toString();
  }
}
