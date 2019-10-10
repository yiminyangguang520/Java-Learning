package com.lee.mybatis.service;

import com.lee.mybatis.entity.Course;
import java.util.List;

/**
 * @author bruce
 */
public interface CourseService {

  int deleteById(Integer id);

  void saveAll(List<Course> courses);

  List<Course> findAll();

  Course findById(Integer id);

  int update(Course course);

  void deleteAll();

}
