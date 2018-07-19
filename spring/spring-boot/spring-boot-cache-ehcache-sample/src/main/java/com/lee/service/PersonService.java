package com.lee.service;

import com.lee.entity.Person;
import java.util.Optional;


/**
 * @author litz-a
 */
public interface PersonService {

  /**
   * save
   * @param person
   * @return
   */
  Person save(Person person);

  /**
   * remove
   * @param id
   */
  void remove(Long id);

  /**
   * findOne
   * @param person
   * @return
   */
  Optional<Person> findOne(Person person);
}
