package com.javainfinite.security.repository;

import com.javainfinite.security.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  public Student findBySname(String username);
}
