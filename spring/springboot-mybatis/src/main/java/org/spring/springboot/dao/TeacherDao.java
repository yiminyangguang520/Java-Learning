package org.spring.springboot.dao;

import org.spring.springboot.domain.Teacher;
import org.springframework.stereotype.Component;

//@Component("myTeacherMapper")
public interface TeacherDao {
    Teacher getById(int id);
}
