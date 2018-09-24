package com.journaldev.spring.di.test;

import com.journaldev.spring.di.consumer.MyXMLApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litz-a
 */
public class ClientXMLApplication {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        "applicationContext.xml");
    MyXMLApplication app = context.getBean(MyXMLApplication.class);

    app.processMessage("Hi Pankaj", "pankaj@abc.com");

    // close the context
    context.close();
  }

}
