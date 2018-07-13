package com.abc.domain;

import java.util.List;

public class Course {

  private int id;
  private String courseCode;  //课程编码
  private String courseName;//课程名称
  private List<Student> students;//选修此课程的学生

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCourseCode() {
    return this.courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  public String getCourseName() {
    return this.courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

}