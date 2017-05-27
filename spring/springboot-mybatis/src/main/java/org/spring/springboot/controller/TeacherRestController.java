package org.spring.springboot.controller;

import org.spring.springboot.domain.Teacher;
import org.spring.springboot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Bruce Lee on 2017/5/26.
 */
@RestController
public class TeacherRestController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/api/teacher", method = RequestMethod.GET)
    public Teacher findOneCity(@RequestParam(value = "teacherId", required = true) int teacherID) {
        return teacherService.getTeacherById(teacherID);
    }
}
