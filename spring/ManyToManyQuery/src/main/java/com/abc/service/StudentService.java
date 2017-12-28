package com.abc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.domain.Student;
import com.abc.mapper.StudentMapper;

@Service
public class StudentService {

  @Autowired
  private StudentMapper studentDao;

  //根据id查找学生
  public Student getById(int id) {
    return this.studentDao.getById(id);
  }

  //修改学生信息
  public void update(Student student) {
    this.studentDao.update(student);
    //this.service();
  }

  public int service() {
    int a = 1 / 0;
    return a;
  }
}
