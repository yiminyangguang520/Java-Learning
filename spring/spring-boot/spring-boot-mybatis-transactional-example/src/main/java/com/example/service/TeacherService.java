package com.example.service;

import com.example.entity.Teacher;

/**
 * @author min
 */
public interface TeacherService {

  /**
   * 根据教师名查找
   * @param name
   * @return
   */
  Teacher findByName(String name);

  /**
   * 插入老师
   * @param teacher
   * @return
   */
  int inserTeacher(Teacher teacher);
}
