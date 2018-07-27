package com.javadeveloperzone.dao;

import com.javadeveloperzone.model.Employee;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by JavaDeveloperZone on 03-08-2017.
 */
@Repository
@Transactional
public interface EmployeeDAO extends CrudRepository<Employee, Long> {

  @Override
  List<Employee> findAll();                           // fetch all Employee
}
