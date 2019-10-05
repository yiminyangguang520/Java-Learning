package org.ostenant.springboot.learning.examples.model.secondary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "course")
@Entity
public class Course implements Serializable {

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "lesson_period")
    private Double lessonPeriod;

    @Column(name = "total_course")
    private Integer totalCourse;

    public Course() {
    }

    public Course(String id, String name, String description, Double lessonPeriod, Integer totalCourse) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lessonPeriod = lessonPeriod;
        this.totalCourse = totalCourse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Double getLessonPeriod() {
        return lessonPeriod;
    }

    public void setLessonPeriod(Double lessonPeriod) {
        this.lessonPeriod = lessonPeriod;
    }

    public Integer getTotalCourse() {
        return totalCourse;
    }

    public void setTotalCourse(Integer totalCourse) {
        this.totalCourse = totalCourse;
    }
}