package com.example.service;

import com.example.entity.Student;

/**
 * @author litz-a
 */
public interface StudentService {

  /**
   * 根据学生名查找
   * @param name
   * @return
   */
  Student findByName(String name);

  /**
   * 插入老师
   * @param student
   * @return
   */
  int insertStudent(Student student);
}
