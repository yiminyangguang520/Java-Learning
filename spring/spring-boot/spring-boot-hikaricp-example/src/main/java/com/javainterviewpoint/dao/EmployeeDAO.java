package com.javainterviewpoint.dao;

import com.javainterviewpoint.entity.Employee;
import java.util.List;

/**
 * @author min
 */
public interface EmployeeDAO {

  /**
   * 查询所有雇员
   * @return
   */
  List<Employee> getAllEmployees();

  /**
   * 根据id查询雇员
   * @param id
   * @return
   */
  Employee getEmployeeById(int id);

  /**
   * 添加雇员
   * @param employee
   */
  void addEmployee(Employee employee);

  /**
   * 更新雇员
   * @param employee
   */
  void updateEmployee(Employee employee);

  /**
   * 根据id删除雇员
   * @param id
   */
  void deleteEmployee(int id);
}

