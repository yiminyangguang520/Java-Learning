package com.javainfinite.security.service;

import com.javainfinite.security.model.Student;
import com.javainfinite.security.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  public Student register(Student student) {
    return studentRepository.save(student);
  }

  public Student getDetails(String username) {
    return studentRepository.findBySname(username);
  }

  public String getStudentRoles(String username) {
    return studentRepository.findBySname(username).getSrole();
  }
}
