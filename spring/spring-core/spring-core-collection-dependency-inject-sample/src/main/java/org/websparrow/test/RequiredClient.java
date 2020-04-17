package org.websparrow.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.websparrow.beans.RequiredType;

/**
 * @author min
 */
public class RequiredClient {

  public static void main(String[] args) {

    ApplicationContext ap = new ClassPathXmlApplicationContext("required.xml");

    RequiredType adv = (RequiredType) ap.getBean("req");
    adv.display();
  }
}
