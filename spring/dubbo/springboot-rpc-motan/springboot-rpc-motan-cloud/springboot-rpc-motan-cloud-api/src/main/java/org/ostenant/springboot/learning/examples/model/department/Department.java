package org.ostenant.springboot.learning.examples.model.department;

import lombok.Data;

import java.io.Serializable;

@Data
public class Department implements Serializable {

    private String id;

    private String name;

    private String description;

}