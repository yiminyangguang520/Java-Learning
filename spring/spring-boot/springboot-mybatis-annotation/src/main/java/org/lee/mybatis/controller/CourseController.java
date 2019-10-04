package org.lee.mybatis.controller;

import java.util.List;
import org.lee.mybatis.model.Course;
import org.lee.mybatis.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bruce
 */
@RestController
public class CourseController implements BaseController<Course, Integer> {

  @Autowired
  private CourseService courseService;

  @Override
  @RequestMapping(value = "/api/course/{id}", method = RequestMethod.DELETE)
  public int deleteById(@PathVariable("id") Integer id) {
    return courseService.deleteById(id);
  }

  @Override
  @RequestMapping(value = "/api/course", method = RequestMethod.POST)
  public int save(@RequestBody Course course) {
    return courseService.save(course);
  }

  @Override
  @RequestMapping(value = "/api/courses", method = RequestMethod.GET)
  public List<Course> findAll() {
    return courseService.findAll();
  }

  @Override
  @RequestMapping(value = "/api/course/{id}", method = RequestMethod.GET)
  public Course findById(@PathVariable("id") Integer id) {
    return courseService.findById(id);
  }

  @RequestMapping(value = "/api/course", method = RequestMethod.PUT)
  public int update(@RequestBody Course course) {
    return courseService.update(course);
  }
}
