package org.ostenant.springboot.learning.examples.service.impl;

import org.ostenant.springboot.learning.examples.model.primary.Department;
import org.ostenant.springboot.learning.examples.repository.primary.JpaDepartmentRepository;
import org.ostenant.springboot.learning.examples.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("jpaDepartmentServiceImpl")
@Transactional
public class JpaDepartmentServiceImpl implements DepartmentService {

    private final JpaDepartmentRepository jpaDepartmentRepository;

    @Autowired
    public JpaDepartmentServiceImpl(JpaDepartmentRepository jpaDepartmentRepository) {
        this.jpaDepartmentRepository = jpaDepartmentRepository;
    }

    @Override
    public int deleteById(String id) {
        jpaDepartmentRepository.delete(id);
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
        Department d = jpaDepartmentRepository.findOne(id);
        Department department = new Department();

        department.setId(d.getId());
        department.setName(d.getName());
        department.setDescription(d.getDescription());

        return department;
    }

    @Override
    public int update(Department department) {
        jpaDepartmentRepository.save(department);
        return 0;
    }
}
