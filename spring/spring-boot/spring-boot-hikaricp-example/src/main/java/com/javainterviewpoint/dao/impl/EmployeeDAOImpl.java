package com.javainterviewpoint.dao.impl;

import com.javainterviewpoint.dao.EmployeeDAO;
import com.javainterviewpoint.entity.Employee;
import com.javainterviewpoint.mapper.EmployeeMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author litz-a
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Employee> getAllEmployees() {
    String sql = "select id, name, age, dept from Employee";
    List<Employee> employeeList = jdbcTemplate.query(sql, new EmployeeMapper());
    return employeeList;
  }

  @Override
  public Employee getEmployeeById(int id) {
    String sql = "select id, name, age, dept from Employee where id = ?";
    Employee employee = (Employee) jdbcTemplate.queryForObject(sql, new EmployeeMapper(), id);
    return employee;
  }

  @Override
  public void addEmployee(Employee employee) {
    String sql = "insert into Employee (id, name, age, dept) values (?, ?, ?, ?)";
    jdbcTemplate.update(sql, employee.getId(), employee.getName(), employee.getAge(), employee.getDept());
  }

  @Override
  public void updateEmployee(Employee employee) {
    String sql = "update Employee set name = ?, age = ?, dept = ? where id = ?";
    jdbcTemplate.update(sql, employee.getName(), employee.getAge(), employee.getDept(), employee.getId());
  }

  @Override
  public void deleteEmployee(int id) {
    String sql = "delete from Employee where id = ?";
    jdbcTemplate.update(sql, id);
  }
}