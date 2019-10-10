package org.lee.mybatis.model;

import java.io.Serializable;

/**
 * @author bruce
 */
public class StudentInfo implements Serializable {

  private Integer id;

  private Integer age;

  private String address;

  private String email;

  private Integer studentId;

  private Student student;

  public Integer getId() {
    return id;
  }

  public StudentInfo withId(Integer id) {
    this.setId(id);
    return this;
  }

  public StudentInfo withAge(Integer age) {
    this.setAge(age);
    return this;
  }

  public StudentInfo withAddress(String address) {
    this.setAddress(address);
    return this;
  }

  public StudentInfo withEmail(String email) {
    this.setEmail(email);
    return this;
  }

  public StudentInfo withStudentId(Integer studentId) {
    this.setStudentId(studentId);
    return this;
  }

  public StudentInfo withStudent(Student student) {
    this.setStudent(student);
    return this;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address == null ? null : address.trim();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email == null ? null : email.trim();
  }

  public Integer getStudentId() {
    return studentId;
  }

  public void setStudentId(Integer studentId) {
    this.studentId = studentId;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
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
    StudentInfo other = (StudentInfo) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
      && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
      && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
      && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
      && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
    result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
    result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
    result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", age=").append(age);
    sb.append(", address=").append(address);
    sb.append(", email=").append(email);
    sb.append(", studentId=").append(studentId);
    sb.append("]");
    return sb.toString();
  }
}
