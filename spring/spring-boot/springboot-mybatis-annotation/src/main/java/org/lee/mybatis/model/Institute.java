package org.lee.mybatis.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

/**
 * @author bruce
 */
public class Institute implements Serializable {

  private Integer id;

  private String name;

  private List<Student> students = new ArrayList<>();

  public Integer getId() {
    return id;
  }

  public Institute withId(Integer id) {
    this.setId(id);
    return this;
  }

  public Institute withName(String name) {
    this.setName(name);
    return this;
  }

  public Institute withStudents(List<Student> students) {
    if (CollectionUtils.isNotEmpty(students)) {
      this.getStudents().addAll(students);
    }
    return this;
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

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
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
    Institute other = (Institute) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
      && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
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
    sb.append("]");
    return sb.toString();
  }
}
