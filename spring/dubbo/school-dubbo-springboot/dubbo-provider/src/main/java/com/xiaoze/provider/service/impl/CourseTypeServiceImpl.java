package com.xiaoze.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoze.api.dao.CourseTypeDao;
import com.xiaoze.api.entity.CourseType;
import com.xiaoze.api.entity.Page;
import com.xiaoze.provider.mapper.CourseTypeMapper;
import com.xiaoze.provider.utils.PageUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * CourseTypeServiceImpl
 *
 * @author xiaoze
 * @date 2018/6/11
 */
@Service(version = "${demo.service.version}",
    application = "${dubbo.application.id}",
    protocol = "${dubbo.protocol.id}",
    registry = "${dubbo.registry.id}"
)
@Transactional(rollbackFor = Exception.class)
public class CourseTypeServiceImpl implements CourseTypeDao {

  @Autowired
  private CourseTypeMapper courseTypeMapper;

  @Override
  public void addCourseType(CourseType courseType) {

    courseTypeMapper.addCourseType(courseType);

  }

  @Override
  public void removeCourseType(Integer typeId) {

    courseTypeMapper.removeCourseType(typeId);

  }

  @Override
  public void updateCourseType(CourseType courseType) {

    courseTypeMapper.updateCourseType(courseType);

  }

  @Override
  public CourseType getCourseTypeById(Integer typeId) {

    return courseTypeMapper.getCourseTypeById(typeId);

  }

  @Override
  public Page<CourseType> loadByPageNo(Integer pageNo) {

    PageHelper.startPage(pageNo, 3);
    List<CourseType> courseTypeList = courseTypeMapper.loadAll();

    PageInfo<CourseType> page = new PageInfo<>(courseTypeList);

    PageUtil pageUtil = new PageUtil();

    return pageUtil.getPage(page);
  }


}
