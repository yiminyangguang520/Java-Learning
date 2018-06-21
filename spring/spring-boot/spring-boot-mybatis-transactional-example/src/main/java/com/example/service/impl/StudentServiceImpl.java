package com.example.service.impl;

import com.example.dao.StudentMapper;
import com.example.entity.Student;
import com.example.entity.StudentExample;
import com.example.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author litz-a
 */
@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentMapper studentMapper;

  /**
   * 根据学生名查找
   */
  @Override
  public Student findByName(String name) {
    StudentExample studentExample = new StudentExample();
    studentExample.createCriteria().andNameEqualTo(name);
    List<Student> students = studentMapper.selectByExample(studentExample);
    if (students.size() > 0) {
      return students.get(0);
    }

    return null;
  }

  /**
   * 插入老师
   */
  @Override
  public int insertStudent(Student student) {
    int result = studentMapper.insertSelective(student);
    return result;
  }
}
