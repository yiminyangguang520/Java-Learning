package org.lee.mybatis.service.impl;

import java.util.List;
import org.lee.mybatis.service.CourseService;
import org.lee.mybatis.mapper.secondary.CourseMapper;
import org.lee.mybatis.model.secondary.Course;
import org.lee.mybatis.model.secondary.CourseExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bruce
 */
@Service("MybatisCourseServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class MybatisCourseServiceImpl implements CourseService {

  private final CourseMapper courseMapper;

  @Autowired
  public MybatisCourseServiceImpl(CourseMapper courseMapper) {
    this.courseMapper = courseMapper;
  }

  @Override
  public int deleteById(String id) {
    return courseMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int save(Course record) {
    return courseMapper.insertSelective(record);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Course> findAll() {
    return courseMapper.selectByExample(new CourseExample());
  }

  @Override
  @Transactional(readOnly = true)
  public Course findById(String id) {
    return courseMapper.selectByPrimaryKey(id);
  }

  @Override
  public int update(Course record) {
    return courseMapper.updateByPrimaryKeySelective(record);
  }

}
