package com.logicbig.example.map.xml;

import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MapInjectionValueExample {

  public static void main(String[] args) {
    ApplicationContext context = new
        ClassPathXmlApplicationContext("Config-value.xml",
        MapInjectionValueExample.class);

    TestBean bean = context.getBean(TestBean.class);
    System.out.println(bean.getMap());
  }

  private static class TestBean {

    private final Map<String, Integer> map;

    private TestBean(Map<String, Integer> map) {
      this.map = map;
    }

    public Map<String, Integer> getMap() {
      return map;
    }
  }
}