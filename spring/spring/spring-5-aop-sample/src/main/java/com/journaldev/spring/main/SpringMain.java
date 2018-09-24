package com.journaldev.spring.main;

import com.journaldev.spring.service.EmployeeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author litz-a
 */
public class SpringMain {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    EmployeeService employeeService = ctx.getBean("employeeService", EmployeeService.class);

    System.out.println(employeeService.getEmployee().getName());

    employeeService.getEmployee().setName("Pankaj");

    employeeService.getEmployee().throwException();

    ctx.close();
  }

}
