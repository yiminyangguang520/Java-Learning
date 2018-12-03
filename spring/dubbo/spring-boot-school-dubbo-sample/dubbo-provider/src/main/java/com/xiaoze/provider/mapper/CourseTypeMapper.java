package com.xiaoze.provider.mapper;

import com.xiaoze.api.entity.CourseType;
import java.util.List;

/**
 * CourseTypeMapper
 *
 * @author xiaoze
 * @date 2018/6/11
 */
public interface CourseTypeMapper {

  void addCourseType(CourseType courseType);

  void removeCourseType(Integer typeId);

  void updateCourseType(CourseType courseType);

  CourseType getCourseTypeById(Integer typeId);

  List<CourseType> loadAll();

}

