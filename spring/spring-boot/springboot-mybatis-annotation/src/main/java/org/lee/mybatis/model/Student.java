package org.lee.mybatis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;

public class Student implements Serializable {

  private Integer id;

  private String name;

  private String grade;

  private String classNumber;

  private Integer instituteId;

  private StudentInfo studentInfo;

  private List<StudentCourse> studentCourses = new ArrayList<>();

  public Student withId(Integer id) {
    this.setId(id);
    return this;
  }

  public Student withName(String name) {
    this.setName(name);
    return this;
  }

  public Student withGrade(String grade) {
    this.setGrade(grade);
    return this;
  }

  public Student withInstituteId(Integer instituteId) {
    this.setInstituteId(instituteId);
    return this;
  }

  public Student withClassNumber(String classNumber) {
    this.setClassNumber(classNumber);
    return this;
  }

  public Student withStudentInfo(StudentInfo studentInfo) {
    this.setStudentInfo(studentInfo);
    return this;
  }

  public Student withStudentCourses(List<StudentCourse> studentCourses) {
    if (CollectionUtils.isNotEmpty(studentCourses)) {
      getStudentCourses().addAll(studentCourses);
    }
    return this;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade == null ? null : grade.trim();
  }

  public String getClassNumber() {
    return classNumber;
  }

  public void setClassNumber(String classNumber) {
    this.classNumber = classNumber == null ? null : classNumber.trim();
  }

  public Integer getInstituteId() {
    return instituteId;
  }

  public void setInstituteId(Integer instituteId) {
    this.instituteId = instituteId;
  }

  public StudentInfo getStudentInfo() {
    return studentInfo;
  }

  public void setStudentInfo(StudentInfo studentInfo) {
    this.studentInfo = studentInfo;
  }

  public List<StudentCourse> getStudentCourses() {
    return studentCourses;
  }

  public void setStudentCourses(List<StudentCourse> studentCourses) {
    this.studentCourses = studentCourses;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (that == null) {
      return false;
    }
    if (getClass() != that.getClass()) {
      return false;
    }
    Student other = (Student) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
      && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
      && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
      && (this.getClassNumber() == null ? other.getClassNumber() == null : this.getClassNumber().equals(other.getClassNumber()))
      && (this.getInstituteId() == null ? other.getInstituteId() == null : this.getInstituteId().equals(other.getInstituteId()));
  }

  @Override
  public int hashCode() {
    Objects.hash(id, name, grade, classNumber, instituteId);
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
    result = prime * result + ((getClassNumber() == null) ? 0 : getClassNumber().hashCode());
    result = prime * result + ((getInstituteId() == null) ? 0 : getInstituteId().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", name=").append(name);
    sb.append(", grade=").append(grade);
    sb.append(", classNumber=").append(classNumber);
    sb.append(", instituteId=").append(instituteId);
    sb.append("]");
    return sb.toString();
  }
}
