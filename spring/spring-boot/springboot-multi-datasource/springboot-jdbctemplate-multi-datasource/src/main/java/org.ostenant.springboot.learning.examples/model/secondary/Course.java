package org.ostenant.springboot.learning.examples.model.secondary;

import java.io.Serializable;

public class Course implements Serializable {

    private String id;

    private String name;

    private String description;

    private Double lessonPeriod;

    private Integer totalCourse;

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