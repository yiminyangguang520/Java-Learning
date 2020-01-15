package org.ostenant.springboot.learning.examples.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {

    private Integer id;

    private String name;

    private String grade;

    private String classNumber;

    private Integer instituteId;

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

}