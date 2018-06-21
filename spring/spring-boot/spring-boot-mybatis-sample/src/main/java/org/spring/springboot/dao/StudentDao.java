package org.spring.springboot.dao;

import org.spring.springboot.domain.Student;

public interface StudentDao {
    Student getById(int id);

    Student getStudentById(int id);

    int add(Student student);
}
