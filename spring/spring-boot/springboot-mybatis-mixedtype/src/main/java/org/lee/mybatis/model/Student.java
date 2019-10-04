package org.lee.mybatis.model;

import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;
import java.util.Objects;
import org.ostenant.springboot.learning.examples.mybatis.utils.JSONAttrGetter;

public class Student implements Serializable {

  /**
   * 主键ID
   */
  private Integer id;

  /**
   * 学生姓名
   */
  private String name;

  /**
   * 学生年级
   */
  private String grade;

  /**
   * 班级
   */
  private String classNumber;

  /**
   * 学院ID
   */
  private Integer instituteId;

  public Integer getId() {
    return id;
  }

  public Student withId(Integer id) {
    this.setId(id);
    return this;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public Student withName(String name) {
    this.setName(name);
    return this;
  }

  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  public String getGrade() {
    return grade;
  }

  public Student withGrade(String grade) {
    this.setGrade(grade);
    return this;
  }

  public void setGrade(String grade) {
    this.grade = grade == null ? null : grade.trim();
  }

  public String getClassNumber() {
    return classNumber;
  }

  public Student withClassNumber(String classNumber) {
    this.setClassNumber(classNumber);
    return this;
  }

  public void setClassNumber(String classNumber) {
    this.classNumber = classNumber == null ? null : classNumber.trim();
  }

  public Integer getInstituteId() {
    return instituteId;
  }

  public Student withInstituteId(Integer instituteId) {
    this.setInstituteId(instituteId);
    return this;
  }

  public void setInstituteId(Integer instituteId) {
    this.instituteId = instituteId;
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
    return Objects.hash(id, name, grade, classNumber, instituteId);
  }

  public static Student fromJson(JSONObject fromJsonObj) {
    if (fromJsonObj == null || fromJsonObj.isEmpty()) {
      return null;
    }
    Student student = new Student();
    student.setId(JSONAttrGetter.getInteger(fromJsonObj, StudentKey.ID));
    student.setName(JSONAttrGetter.getString(fromJsonObj, StudentKey.NAME));
    student.setGrade(JSONAttrGetter.getString(fromJsonObj, StudentKey.GRADE));
    student.setClassNumber(JSONAttrGetter.getString(fromJsonObj, StudentKey.CLASS_NUMBER));
    student.setInstituteId(JSONAttrGetter.getInteger(fromJsonObj, StudentKey.INSTITUTE_ID));
    return student;
  }

  public JSONObject toJson() {
    JSONObject toJsonObj = new JSONObject();
    toJsonObj.put(StudentKey.ID, id);
    toJsonObj.put(StudentKey.NAME, name);
    toJsonObj.put(StudentKey.GRADE, grade);
    toJsonObj.put(StudentKey.CLASS_NUMBER, classNumber);
    toJsonObj.put(StudentKey.INSTITUTE_ID, instituteId);
    return toJsonObj;
  }

  public static final class StudentKey {

    /**
     * 主键ID
     */
    public static final String ID = "id";

    /**
     * 学生姓名
     */
    public static final String NAME = "name";

    /**
     * 学生年级
     */
    public static final String GRADE = "grade";

    /**
     * 班级
     */
    public static final String CLASS_NUMBER = "class_number";

    /**
     * 学院ID
     */
    public static final String INSTITUTE_ID = "institute_id";
  }
}
