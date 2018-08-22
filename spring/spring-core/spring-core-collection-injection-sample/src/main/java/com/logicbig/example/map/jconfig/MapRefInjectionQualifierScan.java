package com.logicbig.example.map.jconfig;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackageClasses = MapRefInjectionQualifierScan.class,
    useDefaultFilters = false,
    includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        value = {MapRefInjectionQualifierScan.class}))
public class MapRefInjectionQualifierScan {


  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(
            MapRefInjectionQualifierScan.class);
    TestBean bean = context.getBean(TestBean.class);
    System.out.println(bean.getMap());
  }

  @Component
  private static class TestBean {

    private Map<String, RefBeanService> map;

    @Autowired
    @Qualifier("myMapRef")
    public void setMap(Map<String, RefBeanService> map) {
      this.map = map;
    }

    public Map<String, RefBeanService> getMap() {
      return map;
    }
  }

  private static abstract class RefBeanService {

    public abstract String getStr();

    @Override
    public String toString() {
      return "RefBean{str='" + getStr() + "'}";
    }

  }

  @Component("bean name 1")
  @Qualifier("myMapRef")
  private static class RefBean extends RefBeanService {

    @Override
    public String getStr() {
      return "one";
    }
  }

  @Component("bean name 2")
  private static class RefBean2 extends RefBeanService {

    @Override
    public String getStr() {
      return "two";
    }
  }

  @Component("bean name 3")
  @Qualifier("myMapRef")
  private static class RefBean3 extends RefBeanService {

    @Override
    public String getStr() {
      return "three";
    }
  }
}