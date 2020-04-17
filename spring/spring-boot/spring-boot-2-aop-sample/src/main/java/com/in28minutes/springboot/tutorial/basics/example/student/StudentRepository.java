package com.in28minutes.springboot.tutorial.basics.example.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
