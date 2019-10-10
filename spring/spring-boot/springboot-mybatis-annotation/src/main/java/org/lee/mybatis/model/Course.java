package org.lee.mybatis.model;

import java.io.Serializable;

/**
 * @author bruce
 */
public class Course implements Serializable {

  private Integer id;

  private String name;

  private Double lessonPeriod;

  private Double score;

  public Course withId(Integer id) {
    this.setId(id);
    return this;
  }

  public Course withName(String name) {
    this.setName(name);
    return this;
  }

  public Course withLessonPeriod(Double lessonPeriod) {
    this.setLessonPeriod(lessonPeriod);
    return this;
  }

  public Course withScore(Double score) {
    this.setScore(score);
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

  public Double getLessonPeriod() {
    return lessonPeriod;
  }

  public void setLessonPeriod(Double lessonPeriod) {
    this.lessonPeriod = lessonPeriod;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
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
    Course other = (Course) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
      && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
      && (this.getLessonPeriod() == null ? other.getLessonPeriod() == null : this.getLessonPeriod().equals(other.getLessonPeriod()))
      && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getLessonPeriod() == null) ? 0 : getLessonPeriod().hashCode());
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
    sb.append(", name=").append(name);
    sb.append(", lessonPeriod=").append(lessonPeriod);
    sb.append(", score=").append(score);
    sb.append("]");
    return sb.toString();
  }
}
