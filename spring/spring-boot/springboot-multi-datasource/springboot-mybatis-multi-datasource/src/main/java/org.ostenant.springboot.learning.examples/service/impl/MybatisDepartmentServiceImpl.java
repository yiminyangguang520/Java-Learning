package org.ostenant.springboot.learning.examples.service.impl;

import org.ostenant.springboot.learning.examples.mapper.primary.DepartmentMapper;
import org.ostenant.springboot.learning.examples.model.primary.Department;
import org.ostenant.springboot.learning.examples.model.primary.DepartmentExample;
import org.ostenant.springboot.learning.examples.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("MybatisDepartmentServiceImpl")
@Transactional
public class MybatisDepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Autowired
    public MybatisDepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public int deleteById(String id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int save(Department record) {
        return departmentMapper.insertSelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        return departmentMapper.selectByExample(new DepartmentExample());
    }

    @Override
    @Transactional(readOnly = true)
    public Department findById(String id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Department record) {
        return departmentMapper.updateByPrimaryKeySelective(record);
    }

}
