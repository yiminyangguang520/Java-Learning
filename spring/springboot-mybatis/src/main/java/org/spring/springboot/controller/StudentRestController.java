package org.spring.springboot.controller;

import org.spring.springboot.domain.Student;
import org.spring.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * Created by Bruce Lee on 2017/5/26.
 */
@RestController
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/api/student", method = RequestMethod.GET)
    public Student findOneStudent(@RequestParam(value = "id") int studentID) {
        return studentService.getStudentInfoById(studentID);
    }

    @RequestMapping(value = "/api/student/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable(value = "id") int studentID) {
        return studentService.getStudent(studentID);
    }

    @RequestMapping(value = "/api/student", method = RequestMethod.POST)
    public int addOneStudent(@RequestParam(value = "studentId") Student student) {
        return studentService.add(student);
    }
}
