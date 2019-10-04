package com.lee.mybatis.service;

import com.lee.mybatis.config.ReadOnlyDataSource;
import com.lee.mybatis.entity.Course;
import com.lee.mybatis.mapper.CourseMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bruce
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl implements CourseService {

  private final CourseMapper courseMapper;

  @Autowired
  public CourseServiceImpl(CourseMapper courseMapper) {
    this.courseMapper = courseMapper;
  }

  @Override
  public int deleteById(Integer id) {
    return courseMapper.deleteById(id);
  }

  @Override
  public void saveAll(List<Course> courses) {
    courses.forEach(courseMapper::save);
  }

  @Override
  @Transactional(readOnly = true)
  @ReadOnlyDataSource
  public List<Course> findAll() {
    return courseMapper.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  @ReadOnlyDataSource
  public Course findById(Integer id) {
    return courseMapper.findById(id);
  }

  @Override
  public int update(Course course) {
    return courseMapper.update(course);
  }

  @Override
  public void deleteAll() {
    courseMapper.deleteAll();
  }

}
