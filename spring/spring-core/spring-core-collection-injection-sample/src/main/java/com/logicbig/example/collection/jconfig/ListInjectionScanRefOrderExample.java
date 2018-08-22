package com.logicbig.example.collection.jconfig;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@PropertySource("classpath:app-props.properties")
@Configuration
@ComponentScan(basePackageClasses = ListInjectionScanRefOrderExample.class,
    useDefaultFilters = false,
    includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        value = {ListInjectionScanRefOrderExample.class}))

public class ListInjectionScanRefOrderExample {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ListInjectionScanRefOrderExample.class);

    TestBean bean = context.getBean(TestBean.class);
    System.out.println(bean.getRefBeanServices());
  }

  @Component
  public static class TestBean {

    private List<RefBeanService> refBeanServices;

    @Autowired
    public void setRefBeanServices(List<RefBeanService> refBeanServices) {
      this.refBeanServices = refBeanServices;
    }

    public List<RefBeanService> getRefBeanServices() {
      return refBeanServices;
    }
  }

  public interface RefBeanService {

    /**
     * getStr
     * @return
     */
    String getStr();
  }

  @Component
  @Order(3)
  public static class RefBean implements RefBeanService {

    private String str;

    @Override
    public String getStr() {
      return str;
    }

    @Value("${some-prop1:defaultStr}")
    public void setStr(String str) {
      this.str = str;
    }

    @Override
    public String toString() {
      return "RefBean{str='" + str + "'}";
    }
  }

  @Component
  @Order(1)
  public static class RefBean2 implements RefBeanService {

    private String str;

    @Override
    public String getStr() {
      return str;
    }

    @Value("${some-prop2:defaultStr}")
    public void setStr(String str) {
      this.str = str;
    }

    @Override
    public String toString() {
      return "RefBean{str='" + str + "'}";
    }
  }

  @Component
  @Order(2)
  public static class RefBean3 implements RefBeanService {

    private String str;

    @Override
    public String getStr() {
      return str;
    }

    @Value("${some-prop3:defaultStr}")
    public void setStr(String str) {
      this.str = str;
    }

    @Override
    public String toString() {
      return "RefBean{ str='" + str + "'}";
    }
  }
}