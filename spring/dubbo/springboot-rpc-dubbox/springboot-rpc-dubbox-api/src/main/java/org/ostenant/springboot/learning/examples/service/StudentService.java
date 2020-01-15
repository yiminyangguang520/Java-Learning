package org.ostenant.springboot.learning.examples.service;


import org.ostenant.springboot.learning.examples.BaseService;
import org.ostenant.springboot.learning.examples.entities.Student;

import java.util.List;


public interface StudentService extends BaseService<Student, Integer> {

    List<Student> saveBatch(List<Student> students);

}
