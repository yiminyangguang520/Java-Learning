package com.journaldev.spring.service;

import com.journaldev.spring.model.Employee;

/**
 * @author litz-a
 */
public class EmployeeService {

  private Employee employee;

  public Employee getEmployee() {
    return this.employee;
  }

  public void setEmployee(Employee e) {
    this.employee = e;
  }
}
