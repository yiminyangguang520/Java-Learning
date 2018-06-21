package org.spring.springboot.service;

import org.spring.springboot.domain.Student;

/**
 * Created by Bruce Lee on 2017/5/26.
 */
public interface StudentService {
    Student getStudentInfoById(int id);
    Student getStudent(int id);
    int add(Student student);
}
