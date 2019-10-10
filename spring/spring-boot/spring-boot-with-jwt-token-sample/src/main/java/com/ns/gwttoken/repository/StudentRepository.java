package com.ns.gwttoken.repository;

import com.ns.gwttoken.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author litz-a
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

}
