package com.logicbig.example.array.jconfig;

import java.util.Arrays;
import javax.annotation.Resource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author min
 */
@Configuration
public class ArrayPropQualifierInjectionExample {

  @Bean
  public TestBean testBean() {
    return new TestBean();
  }


  public String[] strArray() {
    return new String[]{"two", "three", "four"};
  }

  @Bean(name = "myArray")
  public String[] strArray2() {
    return new String[]{"five", "six", "seven"};
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ArrayPropQualifierInjectionExample.class);
    TestBean bean = context.getBean(TestBean.class);
    System.out.println(Arrays.toString(bean.getStringArray()));
  }

  private static class TestBean {

    private String[] stringArray;

    @Resource(name = "myArray")
    public void setStringArray(String[] stringArray) {
      this.stringArray = stringArray;
    }

    public String[] getStringArray() {
      return stringArray;
    }
  }
}