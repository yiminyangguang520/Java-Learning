package com.logicbig.example.map.xml;

import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MapInjectionRefExample {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("config-ref.xml", MapInjectionRefExample.class);
    TestBean bean = context.getBean(TestBean.class);
    System.out.println(bean.getMap());
  }

  private static class TestBean {

    private final Map<String, RefBean> map;

    private TestBean(Map<String, RefBean> map) {
      this.map = map;
    }

    public Map<String, RefBean> getMap() {
      return map;
    }
  }

  private static class RefBean {

    private String str;

    public String getStr() {
      return str;
    }

    public void setStr(String str) {
      this.str = str;
    }

    @Override
    public String toString() {
      return "RefBean{" +
          "str='" + str + '\'' +
          '}';
    }
  }
}