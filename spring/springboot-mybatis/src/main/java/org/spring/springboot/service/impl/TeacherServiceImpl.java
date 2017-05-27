package org.spring.springboot.service.impl;

import org.spring.springboot.dao.TeacherDao;
import org.spring.springboot.domain.Teacher;
import org.spring.springboot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Bruce Lee on 2017/5/26.
 */
@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher getTeacherById(int id) {
        return teacherDao.getById(id);
    }
}
