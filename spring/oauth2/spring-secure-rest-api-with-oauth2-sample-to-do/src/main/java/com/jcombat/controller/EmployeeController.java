package com.jcombat.controller;

import com.jcombat.bean.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeController {

  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  public Employee process(@PathVariable("name") String name, @RequestParam(value = "empId", required = false, defaultValue = "00000") final String id) {
    Employee employee = new Employee();
    employee.setEmpId(id);
    employee.setName(name);
    return employee;
  }
}