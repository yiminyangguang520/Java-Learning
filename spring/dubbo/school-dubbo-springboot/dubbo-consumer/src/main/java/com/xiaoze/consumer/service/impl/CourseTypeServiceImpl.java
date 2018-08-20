package com.xiaoze.consumer.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoze.api.dao.CourseTypeDao;
import com.xiaoze.api.entity.CourseType;
import com.xiaoze.api.entity.Page;
import com.xiaoze.consumer.service.CourseTypeService;
import org.springframework.stereotype.Service;


/**
 * CourseTypeServiceImpl
 *
 * @author xiaoze
 * @date 2018/6/12
 */
@Service
public class CourseTypeServiceImpl implements CourseTypeService {

  @Reference(version = "${demo.service.version}",
      application = "${dubbo.application.id}",
      url = "dubbo://localhost:20880")
  private CourseTypeDao courseTypeDao;

  @Override
  public void addCourseType(CourseType courseType) {

    courseTypeDao.addCourseType(courseType);

  }

  @Override
  public void removeCourseType(Integer typeId) {

    courseTypeDao.removeCourseType(typeId);

  }

  @Override
  public void updateCourseType(CourseType courseType) {

    courseTypeDao.updateCourseType(courseType);

  }

  @Override
  public CourseType getCourseTypeById(Integer typeId) {

    return courseTypeDao.getCourseTypeById(typeId);

  }

  @Override
  public Page<CourseType> loadByPageNo(Integer pageNo) {
    return courseTypeDao.loadByPageNo(pageNo);
  }


}
