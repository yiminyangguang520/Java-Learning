package com.example.service.impl;

import com.example.dao.TeacherMapper;
import com.example.entity.Teacher;
import com.example.entity.TeacherExample;
import com.example.service.TeacherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author min
 */
@Service
public class TeacherServiceImpl implements TeacherService {

  @Autowired
  private TeacherMapper teacherMapper;

  /**
   * 根据教师名查找
   */
  @Override
  public Teacher findByName(String name) {
    TeacherExample teacherExample = new TeacherExample();
    teacherExample.createCriteria().andNameEqualTo(name);
    List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
    if (teachers.size() > 0) {
      return teachers.get(0);
    }

    return null;
  }

  /**
   * 插入老师
   */
  @Override
  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
  public int inserTeacher(Teacher teacher) {
    int count = teacherMapper.insertSelective(teacher);
    return count;
  }
}
