package com.abc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.domain.Course;
import com.abc.mapper.CourseMapper;

@Service
public class CourseService {

  @Autowired
  private CourseMapper courseDao;

  //根据id查找课程
  public Course getById(int id) {
    return this.courseDao.getById(id);
  }

}
