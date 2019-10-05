package org.ostenant.springboot.learning.examples.service.impl;

import org.ostenant.springboot.learning.examples.model.secondary.Course;
import org.ostenant.springboot.learning.examples.repository.secondary.JpaCourseRepository;
import org.ostenant.springboot.learning.examples.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("jpaCourseServiceImpl")
@Transactional
public class JpaCourseServiceImpl implements CourseService {

    private final JpaCourseRepository jpaCourseRepository;

    @Autowired
    public JpaCourseServiceImpl(JpaCourseRepository jpaCourseRepository) {
        this.jpaCourseRepository = jpaCourseRepository;
    }

    @Override
    public int deleteById(String id) {
        jpaCourseRepository.delete(id);
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
        Course c = jpaCourseRepository.findOne(id);
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
        jpaCourseRepository.save(course);
        return 0;
    }
}
