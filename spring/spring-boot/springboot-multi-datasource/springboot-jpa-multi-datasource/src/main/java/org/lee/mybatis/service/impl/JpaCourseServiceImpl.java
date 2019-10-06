package org.lee.mybatis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.lee.mybatis.service.CourseService;
import org.lee.mybatis.model.secondary.Course;
import org.lee.mybatis.repository.secondary.JpaCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bruce
 */
@Service("jpaCourseServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class JpaCourseServiceImpl implements CourseService {

  private final JpaCourseRepository jpaCourseRepository;

  @Autowired
  public JpaCourseServiceImpl(JpaCourseRepository jpaCourseRepository) {
    this.jpaCourseRepository = jpaCourseRepository;
  }

  @Override
  public int deleteById(String id) {
    jpaCourseRepository.deleteById(id);
    return 0;
  }

  @Override
  public int save(Course course) {
    jpaCourseRepository.save(course);
    return 0;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Course> findAll() {
    List<Course> allCourses = jpaCourseRepository.findAll();
    List<Course> courses = new ArrayList<>();

    for (Course c : allCourses) {
      Course course = new Course();

      course.setId(c.getId());
      course.setName(c.getName());
      course.setDescription(c.getDescription());
      course.setTotalCourse(c.getTotalCourse());
      course.setLessonPeriod(c.getLessonPeriod());

      courses.add(course);
    }

    return courses;
  }

  @Override
  @Transactional(readOnly = true)
  public Course findById(String id) {
    Optional<Course> courseOptional = jpaCourseRepository.findById(id);
    Course course = new Course();

    courseOptional.ifPresent(c -> {
      course.setId(c.getId());
      course.setName(c.getName());
      course.setDescription(c.getDescription());
      course.setTotalCourse(c.getTotalCourse());
      course.setLessonPeriod(c.getLessonPeriod());
    });

    return course;
  }

  @Override
  public int update(Course course) {
    jpaCourseRepository.save(course);
    return 0;
  }
}
