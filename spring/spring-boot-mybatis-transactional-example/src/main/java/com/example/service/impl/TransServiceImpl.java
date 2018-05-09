package com.example.service.impl;

import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.service.StudentService;
import com.example.service.TeacherService;
import com.example.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author litz-a
 */
@Service
public class TransServiceImpl implements TransService {

  @Autowired
  private TeacherService teacherService;

  @Autowired
  private StudentService studentService;

  /**
   * 操作1
   */
  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
  public void operate1() throws Exception {
    Student student = new Student();
    student.setName("王二");
    student.setSex("男");
    studentService.insertStudent(student);

    try {
      operate2();
    } catch (Exception e) {
      e.printStackTrace();
    }

    student.setName("麻子");
    student.setSex("男");
    studentService.insertStudent(student);
    throw new Exception("operator 1");
  }

  /**
   * 操作2
   * @throws
   */
  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
  public void operate2() throws Exception {
    Teacher teacher = new Teacher();
    teacher.setName("小妖");
    teacher.setSex("男");
    teacher.setMajor("数据挖掘");
    teacherService.inserTeacher(teacher);
    throw new Exception("测试");
  }
}
