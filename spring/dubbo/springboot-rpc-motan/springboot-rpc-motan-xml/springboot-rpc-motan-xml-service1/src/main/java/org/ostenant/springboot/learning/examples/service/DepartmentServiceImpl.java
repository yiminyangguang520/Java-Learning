package org.ostenant.springboot.learning.examples.service;

import org.ostenant.springboot.learning.examples.model.department.Department;
import org.ostenant.springboot.learning.examples.repository.DepartmentRepository;
import org.ostenant.springboot.learning.examples.service.department.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public int deleteById(String id) {
        departmentRepository.deleteById(id);
        return 0;
    }

    @Override
    public int save(Department department) {
        departmentRepository.save(department);
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        List<Department> allDepartments = departmentRepository.findAll();
        List<Department> departments = new ArrayList<>();

        for (Department d : allDepartments) {
            Department department = new Department();

            department.setId(d.getId());
            department.setDescription(d.getDescription());
            department.setName(d.getName());

            departments.add(department);
        }

        return departments;
    }

    @Override
    @Transactional(readOnly = true)
    public Department findById(String id) {
        Department d = departmentRepository.findById(id);
        Department department = new Department();

        department.setId(d.getId());
        department.setName(d.getName());
        department.setDescription(d.getDescription());

        return department;
    }

    @Override
    public int update(Department department) {
        departmentRepository.save(department);
        return 0;
    }
}
