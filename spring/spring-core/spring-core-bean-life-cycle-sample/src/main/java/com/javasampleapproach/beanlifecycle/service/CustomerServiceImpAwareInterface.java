package com.javasampleapproach.beanlifecycle.service;

import com.javasampleapproach.beanlifecycle.bean.Customer;
import java.util.Arrays;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * @author min
 */
public class CustomerServiceImpAwareInterface implements ApplicationContextAware, ApplicationEventPublisherAware,
    BeanClassLoaderAware, BeanFactoryAware, BeanNameAware, EnvironmentAware, ResourceLoaderAware {

  private Customer customer;
  private String[] beanNames;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String[] getBeanNames() {
    return beanNames;
  }

  public void setBeanNames(String[] beanNames) {
    this.beanNames = beanNames;
  }

  public CustomerServiceImpAwareInterface() {
    System.out.println("Call constructor for CustomerServiceImpAwareInterface");
  }

  @Override
  public void setBeanName(String beanName) {
    System.out.println("setBeanName()");
    System.out.println("Current Bean Name: " + beanName);
  }

  @Override
  public void setBeanClassLoader(ClassLoader classLoader) {
    System.out.println("setBeanClassLoader()");
    System.out.println("Current ClassLoader Name: " + classLoader.getClass().getName());
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    System.out.println("setBeanFactory()");
    for (String beanName : beanNames) {
      System.out.println(
          "The BeanFactory contains Bean \"" + beanName + "\"? : " + beanFactory.containsBean(beanName));
    }
  }

  @Override
  public void setEnvironment(Environment environment) {
    System.out.println("setEnvironment()");
  }

  @Override
  public void setResourceLoader(ResourceLoader resourceLoader) {
    System.out.println("setResourceLoader()");
    Resource resource = resourceLoader.getResource("classpath:bean_imp_aware.xml");
    System.out.println("Current Resource File Name:" + resource.getFilename());
  }

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    System.out.println("setApplicationEventPublisher()");
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    System.out.println("setApplicationContext()");
    System.out.println("All Beans: " + Arrays.toString(context.getBeanDefinitionNames()));
  }

}
