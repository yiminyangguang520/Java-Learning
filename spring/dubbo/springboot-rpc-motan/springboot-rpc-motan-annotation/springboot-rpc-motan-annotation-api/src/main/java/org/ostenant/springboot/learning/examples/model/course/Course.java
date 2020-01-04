package org.ostenant.springboot.learning.examples.model.course;

import lombok.Data;

import java.io.Serializable;

@Data
public class Course implements Serializable {

    private String id;

    private String name;

    private String description;

    private Double lessonPeriod;

    private Integer totalCourse;

}