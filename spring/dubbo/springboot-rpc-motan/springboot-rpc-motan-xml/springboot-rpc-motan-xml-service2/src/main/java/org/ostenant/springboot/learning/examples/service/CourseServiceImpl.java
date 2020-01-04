package org.ostenant.springboot.learning.examples.service;

import org.ostenant.springboot.learning.examples.model.course.Course;
import org.ostenant.springboot.learning.examples.repository.CourseRepository;
import org.ostenant.springboot.learning.examples.service.course.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class CourseServiceImpl implements ICourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public int deleteById(String id) {
        courseRepository.deleteById(id);
        return 0;
    }

    @Override
    public int save(Course course) {
        courseRepository.save(course);
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        List<Course> allCourses = courseRepository.findAll();
        List<Course> courses = new ArrayList<>();

        for (Course c : allCourses) {
            Course course = new Course();

            course.setId(c.getId());
            course.setName(c.getName());
            course.setTotalCourse(c.getTotalCourse());
            course.setDescription(c.getDescription());
            course.setLessonPeriod(c.getLessonPeriod());

            courses.add(course);
        }

        return courses;
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(String id) {
        Course c = courseRepository.findById(id);
        Course course = new Course();

        course.setId(c.getId());
        course.setName(c.getName());
        course.setDescription(c.getDescription());
        course.setTotalCourse(c.getTotalCourse());
        course.setLessonPeriod(c.getLessonPeriod());

        return course;
    }

    @Override
    public int update(Course course) {
        courseRepository.save(course);
        return 0;
    }
}
