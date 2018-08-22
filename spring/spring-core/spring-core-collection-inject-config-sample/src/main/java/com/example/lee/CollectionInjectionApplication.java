package com.example.lee;

import com.example.lee.bean.CollectionConfig;
import com.example.lee.bean.CollectionsBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author litz-a
 */
@SpringBootApplication
public class CollectionInjectionApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(CollectionInjectionApplication.class, args);
  }

  /**
   * Callback used to run the bean.
   *
   * @param args incoming main method arguments
   * @throws Exception on error
   */
  @Override
  public void run(String... args) throws Exception {
    ApplicationContext context = new AnnotationConfigApplicationContext(CollectionConfig.class);
    CollectionsBean collectionsBean = context.getBean(CollectionsBean.class);
    collectionsBean.printNameList();
    collectionsBean.printNameSet();
    collectionsBean.printNameMap();
    collectionsBean.printBeanList();
  }
}
