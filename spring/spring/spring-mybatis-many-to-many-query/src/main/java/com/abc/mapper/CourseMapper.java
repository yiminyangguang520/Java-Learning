package com.abc.mapper;

import org.springframework.stereotype.Repository;

import com.abc.domain.Course;

@Repository
public interface CourseMapper {

  Course getById(int id);
}
