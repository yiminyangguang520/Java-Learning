package org.lee.mybatis.service.impl;

import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.lee.mybatis.mapper.StudentMapper;
import org.lee.mybatis.model.Student;
import org.lee.mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentMapper studentMapper;

  @Override
  public int deleteById(Integer id) {
    return studentMapper.deleteById(id);
  }

  @Override
  public int save(Student record) {
    return studentMapper.save(record);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Student> findAll() {
    return studentMapper.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Student findById(Integer id) {
    return studentMapper.findById(id);
  }

  @Override
  public int update(Student record) {
    return studentMapper.update(record);
  }

  @Override
  public List<Student> saveBatch(List<Student> students) {
    if (CollectionUtils.isNotEmpty(students)) {
      studentMapper.saveBatch(students);
      return students;
    }
    return null;
  }
}
