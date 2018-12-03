package com.xiaoze.consumer.controller;

import com.xiaoze.api.entity.CourseType;
import com.xiaoze.api.entity.Page;
import com.xiaoze.consumer.service.CourseTypeService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * SecurityController
 *
 * @author xiaoze
 * @date 2018/6/12
 */
@Controller
@RequestMapping("/courseType")
public class CourseTypeController {

  @Autowired
  private CourseTypeService courseTypeService;

  /**
   * 访问课程类型输入界面
   */
  @GetMapping("/toInput")
  public String input(Map<String, Object> map) {
    map.put("courseType", new CourseType());

    return "courseType/input_course_type";
  }

  /**
   * 创建新课程类型
   */
  @PostMapping(value = "/create")
  public String create(CourseType courseType) {

    courseTypeService.addCourseType(courseType);

    return "redirect:/courseType/list";

  }

  @GetMapping("/list")
  public String list(Map<String, Object> map, @RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr) {

    int pageNo = 1;

    //对 pageNo 的校验
    pageNo = Integer.parseInt(pageNoStr);
    if (pageNo < 1) {
      pageNo = 1;
    }

    Page<CourseType> page = courseTypeService.loadByPageNo(pageNo);

    map.put("page", page);

    return "courseType/list_course_type";
  }

  @DeleteMapping(value = "/remove/{typeId}")
  public String remove(@PathVariable("typeId") Integer typeId) {

    courseTypeService.removeCourseType(typeId);

    return "redirect:/courseType/list";
  }

  @GetMapping(value = "/preUpdate/{typeId}")
  public String preUpdate(@PathVariable("typeId") Integer typeId, Map<String, Object> map) {
    System.out.println(courseTypeService.getCourseTypeById(typeId));
    map.put("courseType", courseTypeService.getCourseTypeById(typeId));

    return "courseType/update_course_type";
  }

  @PutMapping(value = "/update")
  public String update(CourseType courseType) {

    courseTypeService.updateCourseType(courseType);

    return "redirect:/courseType/list";
  }

}
