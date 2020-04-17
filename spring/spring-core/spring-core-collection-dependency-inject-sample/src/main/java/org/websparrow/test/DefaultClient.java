package org.websparrow.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.websparrow.beans.DefaultType;

/**
 * @author min
 */
public class DefaultClient {

  public static void main(String[] args) {

    ApplicationContext ap = new ClassPathXmlApplicationContext("default.xml");

    DefaultType def = (DefaultType) ap.getBean("def");
    def.display();
  }
}
