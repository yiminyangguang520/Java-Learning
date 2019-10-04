package com.lee.mybatis;

import com.lee.mybatis.entity.Course;
import com.lee.mybatis.service.CourseService;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisDynamicDataSourceApplicationTests {


  @Autowired
  private CourseService courseService;

  @Test
  public void testSaveCourse() throws Exception {
    courseService.saveAll(Arrays.asList(
      Course.builder().id(1)
        .name("English")
        .lessonPeriod(90.0)
        .score(90.0)
        .build(),
      Course.builder().id(2)
        .name("Math")
        .lessonPeriod(90.0)
        .score(95.0)
        .build()));

  }

  @Test
  public void testUpdateCourse() throws Exception {
    courseService.update(Course.builder()
      .id(0).score(95.0)
      .build());
  }

  @Test
  public void testFindAllCourses() throws Exception {
    List<Course> courses = courseService.findAll();
    MatcherAssert.assertThat(courses.size(), IsEqual.equalTo(2));
    MatcherAssert.assertThat(courses.get(0), IsEqual.equalTo(Course.builder().id(1)
      .name("English")
      .lessonPeriod(90.0)
      .score(90.0)
      .build()));

    MatcherAssert.assertThat(courses.get(1), IsEqual.equalTo(Course.builder().id(2)
      .name("Math")
      .lessonPeriod(90.0)
      .score(95.0)
      .build()));
  }

  @Test
  public void testFindCourseById() throws Exception {
    Course course = courseService.findById(1);
    MatcherAssert.assertThat(course, IsEqual.equalTo(Course.builder().id(1)
      .name("English")
      .lessonPeriod(90.0)
      .score(95.0)
      .build()));
  }

  @Test
  public void testDeleteCourseById() throws Exception {
    courseService.deleteById(1);
  }

  @Test
  public void testDeleteAll() {
    courseService.deleteAll();
  }

}
