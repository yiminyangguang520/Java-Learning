package com.javadeveloperzone.service;

import com.javadeveloperzone.dao.EmployeeDAO;
import com.javadeveloperzone.model.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by JavaDeveloperZone on 04-04-2018.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeDAO employeeDAO;

  @Override
  @Cacheable(value = "employees")           // it will cache result and key name will be "employees"
  public List<Employee> findAll() {
    return employeeDAO.findAll();
  }

  @Override
  @CacheEvict(value = "employees", allEntries = true)      // It will clear cache when delete any employee to database
  public void delete(long employeeId) {
    Optional<Employee> employee = employeeDAO.findById(employeeId);
    if (employee.isPresent()) {
      employeeDAO.delete(employee.get());
    }
  }

  @Override
  @CacheEvict(value = "employees", allEntries = true)       // It will clear cache when new employee save to database
  public Employee save(Employee employee) {
    return employeeDAO.save(employee);
  }
}
