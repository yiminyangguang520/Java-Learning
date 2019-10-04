package org.lee.mybatis.service.impl;

import java.util.List;
import org.lee.mybatis.mapper.StudentInfoMapper;
import org.lee.mybatis.model.StudentInfo;
import org.lee.mybatis.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author bruce
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StudentInfoServiceImpl implements StudentInfoService {

  @Autowired
  private StudentInfoMapper studentInfoMapper;

  @Override
  public int deleteById(Integer id) {
    return studentInfoMapper.deleteById(id);
  }

  @Override
  public int save(StudentInfo studentInfo) {
    return studentInfoMapper.save(studentInfo);
  }

  @Override
  @Transactional(readOnly = true)
  public List<StudentInfo> findAll() {
    return studentInfoMapper.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public StudentInfo findById(Integer id) {
    return studentInfoMapper.findById(id);
  }

  @Override
  public int update(StudentInfo studentInfo) {
    return studentInfoMapper.update(studentInfo);
  }
}
