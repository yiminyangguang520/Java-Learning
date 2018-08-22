package com.logicbig.example.collection.xml;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ListInjectionRefExample {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("config-ref.xml", ListInjectionRefExample.class);
    TestBean bean = context.getBean(TestBean.class);
    System.out.println(bean.getRefBeanList());
  }

  private static class TestBean {

    private final List<RefBean> refBeanList;


    private TestBean(List<RefBean> refBeanList) {
      this.refBeanList = refBeanList;
    }

    public List<RefBean> getRefBeanList() {
      return refBeanList;
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