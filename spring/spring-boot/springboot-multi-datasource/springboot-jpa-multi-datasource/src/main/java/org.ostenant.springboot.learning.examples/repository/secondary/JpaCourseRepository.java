package org.ostenant.springboot.learning.examples.repository.secondary;

import org.ostenant.springboot.learning.examples.model.secondary.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCourseRepository extends JpaRepository<Course, String> {
}
