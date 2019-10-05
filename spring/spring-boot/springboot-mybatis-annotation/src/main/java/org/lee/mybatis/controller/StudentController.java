package org.lee.mybatis.controller;

import java.util.List;
import org.lee.mybatis.model.Student;
import org.lee.mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bruce
 */
@RestController
public class StudentController implements BaseController<Student, Integer> {

  @Autowired
  private StudentService studentService;

  @Override
  @RequestMapping(value = "/api/student/{id}", method = RequestMethod.DELETE)
  public int deleteById(@PathVariable("id") Integer id) {
    return studentService.deleteById(id);
  }

  @Override
  @RequestMapping(value = "/api/student", method = RequestMethod.POST)
  public int save(@RequestBody Student student) {
    return studentService.save(student);
  }

  @Override
  @RequestMapping(value = "/api/students", method = RequestMethod.GET)
  public List<Student> findAll() {
    return studentService.findAll();
  }

  @Override
  @RequestMapping(value = "/api/student/{id}", method = RequestMethod.GET)
  public Student findById(@PathVariable("id") Integer id) {
    return studentService.findById(id);
  }

  @Override
  @RequestMapping(value = "/api/student", method = RequestMethod.PUT)
  public int update(@RequestBody Student student) {
    return studentService.update(student);
  }

  @RequestMapping(value = "/api/students", method = RequestMethod.POST)
  public List<Student> saveBatch(@RequestBody List<Student> students) {
    List<Student> studentList = studentService.saveBatch(students);
    return studentList;
  }

}
