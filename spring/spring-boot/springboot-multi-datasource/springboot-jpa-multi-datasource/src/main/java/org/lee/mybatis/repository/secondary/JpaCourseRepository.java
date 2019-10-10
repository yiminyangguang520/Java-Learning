package org.lee.mybatis.repository.secondary;

import org.lee.mybatis.model.secondary.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bruce
 */
public interface JpaCourseRepository extends JpaRepository<Course, String> {

}
