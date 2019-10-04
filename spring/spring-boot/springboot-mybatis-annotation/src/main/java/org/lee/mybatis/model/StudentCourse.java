package org.lee.mybatis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

/**
 * @author bruce
 */
public class StudentCourse implements Serializable {

  private Integer id;

  private Integer studentId;

  private Integer courseId;

  private Double score;

  private List<Course> courses = new ArrayList<>();

  public Integer getId() {
    return id;
  }

  public StudentCourse withId(Integer id) {
    this.setId(id);
    return this;
  }

  public StudentCourse withStudentId(Integer studentId) {
    this.setStudentId(studentId);
    return this;
  }

  public StudentCourse withCourseId(Integer courseId) {
    this.setCourseId(courseId);
    return this;
  }

  public StudentCourse withScore(Double score) {
    this.setScore(score);
    return this;
  }

  public StudentCourse withStudentCourse(List<Course> courses) {
    if (CollectionUtils.isNotEmpty(courses)) {
      getCourses().addAll(courses);
    }
    return this;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getStudentId() {
    return studentId;
  }

  public void setStudentId(Integer studentId) {
    this.studentId = studentId;
  }

  public Integer getCourseId() {
    return courseId;
  }

  public void setCourseId(Integer courseId) {
    this.courseId = courseId;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
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
    StudentCourse other = (StudentCourse) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
      && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
      && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
      && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
    result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
    result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", studentId=").append(studentId);
    sb.append(", courseId=").append(courseId);
    sb.append(", score=").append(score);
    sb.append("]");
    return sb.toString();
  }
}
