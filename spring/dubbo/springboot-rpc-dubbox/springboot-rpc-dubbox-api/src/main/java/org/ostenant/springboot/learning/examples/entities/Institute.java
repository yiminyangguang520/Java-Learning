package org.ostenant.springboot.learning.examples.entities;


import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Institute implements Serializable {

    private Integer id;

    private String name;

    private List<Student> students = new ArrayList<>();

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

}