package org.ostenant.springboot.learning.examples.service.impl;

import org.ostenant.springboot.learning.examples.mapper.secondary.CourseMapper;
import org.ostenant.springboot.learning.examples.model.secondary.Course;
import org.ostenant.springboot.learning.examples.model.secondary.CourseExample;
import org.ostenant.springboot.learning.examples.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("MybatisCourseServiceImpl")
@Transactional
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
