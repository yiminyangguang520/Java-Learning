package com.jcg.examples.test;

import com.jcg.examples.bo.BusinessTargetObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author litz-a
 */
public class TestLoggingAspect {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new ClassPathResource("spring-configuration.xml").getPath());
    BusinessTargetObject target = context.getBean(BusinessTargetObject.class);
    target.sayHello();
    target.performTransaction("JavaCodeGeeks");
    target.merryGoAround();
    target.throwException();
    context.close();
  }
}
