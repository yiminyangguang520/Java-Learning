package com.journaldev.spring.di.test;

import com.journaldev.spring.di.configuration.DIConfiguration;
import com.journaldev.spring.di.consumer.MyApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author min
 */
public class ClientApplication {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
    MyApplication app = context.getBean(MyApplication.class);

    app.processMessage("Hi Pankaj", "pankaj@abc.com");

    //close the context
    context.close();
  }

}
