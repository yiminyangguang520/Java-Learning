package com.javasampleapproach.beanpostprocessors.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author min
 */
public class CustomBeanPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("postProcessBeforeInitialization() for " + beanName + " - implements BeanPostProcessor");
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("postProcessAfterInitialization() for " + beanName + " - implements BeanPostProcessor");
    return bean;
  }
}
