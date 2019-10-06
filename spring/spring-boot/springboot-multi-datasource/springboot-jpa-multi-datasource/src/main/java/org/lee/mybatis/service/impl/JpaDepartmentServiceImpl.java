package org.lee.mybatis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.lee.mybatis.model.primary.Department;
import org.lee.mybatis.repository.primary.JpaDepartmentRepository;
import org.lee.mybatis.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bruce
 */
@Service("jpaDepartmentServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class JpaDepartmentServiceImpl implements DepartmentService {

  private final JpaDepartmentRepository jpaDepartmentRepository;

  @Autowired
  public JpaDepartmentServiceImpl(JpaDepartmentRepository jpaDepartmentRepository) {
    this.jpaDepartmentRepository = jpaDepartmentRepository;
  }

  @Override
  public int deleteById(String id) {
    jpaDepartmentRepository.deleteById(id);
    return 0;
  }

  @Override
  public int save(Department department) {
    jpaDepartmentRepository.save(department);
    return 0;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Department> findAll() {
    List<Department> allDepartments = jpaDepartmentRepository.findAll();
    List<Department> departments = new ArrayList<>();

    for (Department d : allDepartments) {
      Department department = new Department();

      department.setId(d.getId());
      department.setName(d.getName());
      department.setDescription(d.getDescription());

      departments.add(department);
    }

    return departments;
  }

  @Override
  @Transactional(readOnly = true)
  public Department findById(String id) {
    Optional<Department> de = jpaDepartmentRepository.findById(id);
    Department department = new Department();

    de.ifPresent(d -> {
      department.setId(d.getId());
      department.setName(d.getName());
      department.setDescription(d.getDescription());
    });

    return department;
  }

  @Override
  public int update(Department department) {
    jpaDepartmentRepository.save(department);
    return 0;
  }
}
