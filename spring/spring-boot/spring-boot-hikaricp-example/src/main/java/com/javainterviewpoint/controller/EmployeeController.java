package com.javainterviewpoint.controller;

import com.javainterviewpoint.dao.impl.EmployeeDAOImpl;
import com.javainterviewpoint.entity.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class EmployeeController {

  @Autowired
  private EmployeeDAOImpl employeeDAOImpl;

  @GetMapping("/employees")
  public ResponseEntity<List<Employee>> getAllEmployees() {
    List<Employee> employeeList = employeeDAOImpl.getAllEmployees();
    return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
  }

  @GetMapping("/employee/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
    Employee employee = employeeDAOImpl.getEmployeeById(id);
    return new ResponseEntity<Employee>(employee, HttpStatus.OK);
  }

  @PostMapping("/employee")
  public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
    employeeDAOImpl.addEmployee(employee);
    return new ResponseEntity<Employee>(employee, HttpStatus.OK);
  }

  @PutMapping("/employee")
  public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
    employeeDAOImpl.updateEmployee(employee);
    return new ResponseEntity<Employee>(employee, HttpStatus.OK);
  }

  @DeleteMapping("/employee/{id}")
  public ResponseEntity deleteEmployee(@PathVariable("id") Integer id) {
    employeeDAOImpl.deleteEmployee(id);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}