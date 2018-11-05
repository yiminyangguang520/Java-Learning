package com.spring;

import com.spring.pojo.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litz-a
 */
public class AppMain {

  public static void main(String[] args) {
    ApplicationContext ac = new ClassPathXmlApplicationContext("required-annotation.xml");

    Employee emp = ac.getBean("myemployee", Employee.class);
    System.out.println(emp.toString());
  }
}