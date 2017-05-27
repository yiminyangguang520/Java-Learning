package org.spring.springboot.service.impl;

import org.spring.springboot.dao.StudentDao;
import org.spring.springboot.domain.Student;
import org.spring.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Bruce Lee on 2017/5/26.
 */
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student getStudentInfoById(int id) {
        return studentDao.getById(id);
    }

    @Override
    public Student getStudent(int id) {
        return studentDao.getStudentById(id);
    }

    @Override
    public int add(Student student) {
        return studentDao.add(student);
    }
}
