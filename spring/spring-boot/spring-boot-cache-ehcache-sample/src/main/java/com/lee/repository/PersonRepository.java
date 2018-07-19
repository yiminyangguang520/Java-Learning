package com.lee.repository;

import com.lee.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author litz-a
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}