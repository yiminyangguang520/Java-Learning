package com.xiaoze.consumer.service;


import com.xiaoze.api.entity.CourseType;
import com.xiaoze.api.entity.Page;

/**
 * CourseTypeService
 *
 * @author xiaoze
 * @date 2018/6/12
 */
public interface CourseTypeService {

  /**
   * 新增一条课程类型记录
   */
  void addCourseType(CourseType courseType);

  /**
   * 删除一条课程类型记录
   */
  void removeCourseType(Integer typeId);

  /**
   * 更新一条课程类型记录
   */
  void updateCourseType(CourseType courseType);

  /**
   * 获取一条课程类型记录
   *
   * @return CourseType
   */
  CourseType getCourseTypeById(Integer typeId);

  /**
   * 获取课程类型记录
   *
   * @return List
   */
  Page<CourseType> loadByPageNo(Integer pageNo);

}
