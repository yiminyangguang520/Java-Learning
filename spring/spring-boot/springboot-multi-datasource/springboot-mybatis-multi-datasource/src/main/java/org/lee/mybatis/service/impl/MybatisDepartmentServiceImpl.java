package org.lee.mybatis.service.impl;

import java.util.List;
import org.lee.mybatis.mapper.primary.DepartmentMapper;
import org.lee.mybatis.model.primary.Department;
import org.lee.mybatis.model.primary.DepartmentExample;
import org.lee.mybatis.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bruce
 */
@Service("MybatisDepartmentServiceImpl")
@Transactional(rollbackFor = Exception.class)
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
