package com.javainfinite.security.controller;

import com.javainfinite.security.model.Student;
import com.javainfinite.security.service.StudentService;
import com.javainfinite.security.util.JwtTokenCreator;
import com.javainfinite.security.util.JwtTokenValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  Logger logger = LoggerFactory.getLogger(StudentController.class);

  private StudentService service;

  private PasswordEncoder encoder;

  public StudentController(StudentService service, PasswordEncoder encoder) {
    this.service = service;
    this.encoder = encoder;
  }

  /**
   * Any user can access this API - No Authentication required
   *
   * @param student
   * @return
   */

  @PostMapping("/register")
  public Student registerStudent(@RequestBody Student student) {
    Student student1 = new Student();
    logger.info("Student name: {}", student.getSname());
    student1.setSname(student.getSname());
    student1.setPassword(encoder.encode(student.getPassword()));
    student1.setSrole(student.getSrole());
    return service.register(student1);
  }

  /**
   * user login
   *
   * @return
   */
  @PostMapping("/login")
  public String login() {
    return "Successfully logged in by user: " + SecurityContextHolder.getContext().getAuthentication().getName();
  }

  /**
   * User who has logged in successfully can access this API
   *
   * @param username
   * @return
   */
  @GetMapping("/studentInfo")
  public Student getStudentInfo(@RequestParam("sname") String username) {
    return service.getDetails(username);
  }

  /**
   * User who has the role ROLE_WRITE can only access this API
   *
   * @param username
   * @return
   */
  @GetMapping("/getStudentRoles")
  public String getStudentRoles(@RequestParam("sname") String username) {
    return service.getStudentRoles(username);
  }

  /**
   * Token expired, validate the refresh token and then generate a new token
   *
   * @param request
   * @param response
   */
  @GetMapping("/refreshToken")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
    logger.info("Inside refresh token...");
    JwtTokenCreator generator = new JwtTokenCreator();
    JwtTokenValidator validation = new JwtTokenValidator();
    validation.validateJwtToken(request, response, true);
    generator.generateToken(request, response);
  }

}
