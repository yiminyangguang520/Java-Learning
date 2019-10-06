package org.lee.mybatis.controller;

import java.util.List;
import org.lee.mybatis.service.CourseService;
import org.lee.mybatis.model.secondary.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bruce
 */
@RestController
public class CourseController implements BasicController<Course, String> {

  private final CourseService courseService;

  @Autowired
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @Override
  @RequestMapping(value = "/api/course/{id}", method = RequestMethod.DELETE)
  public int deleteById(@PathVariable("id") String id) {
    return courseService.deleteById(id);
  }

  @Override
  @RequestMapping(value = "/api/course", method = RequestMethod.POST)
  public int save(Course course) {
    return courseService.save(course);
  }

  @Override
  @RequestMapping(value = "/api/courses", method = RequestMethod.GET)
  public List<Course> findAll() {
    return courseService.findAll();
  }

  @Override
  @RequestMapping(value = "/api/course/{id}", method = RequestMethod.GET)
  public Course findById(@PathVariable("id") String id) {
    return courseService.findById(id);
  }

  @Override
  @RequestMapping(value = "/api/course", method = RequestMethod.PUT)
  public int update(Course course) {
    return courseService.update(course);
  }
}
