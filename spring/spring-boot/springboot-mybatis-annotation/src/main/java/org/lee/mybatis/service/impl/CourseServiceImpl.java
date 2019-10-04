package org.lee.mybatis.service.impl;

import java.util.List;
import org.lee.mybatis.mapper.CourseMapper;
import org.lee.mybatis.model.Course;
import org.lee.mybatis.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author bruce
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl implements CourseService {

  @Autowired
  private CourseMapper courseMapper;

  @Override
  public int deleteById(Integer id) {
    return courseMapper.deleteById(id);
  }

  @Override
  public int save(Course record) {
    return courseMapper.save(record);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Course> findAll() {
    return courseMapper.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Course findById(Integer id) {
    return courseMapper.findById(id);
  }

  @Override
  public int update(Course course) {
    return courseMapper.update(course);
  }

}
