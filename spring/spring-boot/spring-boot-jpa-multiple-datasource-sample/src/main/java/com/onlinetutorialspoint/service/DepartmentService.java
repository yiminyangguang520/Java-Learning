package com.onlinetutorialspoint.service;

import com.onlinetutorialspoint.model.db2.Department;
import com.onlinetutorialspoint.repository.db2.DepartmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service
public class DepartmentService {

  @Autowired
  private DepartmentRepository deptRepo;

  public List<Department> getAllDepartment() {
    return (List<Department>) deptRepo.findAll();
  }

  public Department saveDepartment(Department dept) {
    return deptRepo.save(dept);
  }
}
