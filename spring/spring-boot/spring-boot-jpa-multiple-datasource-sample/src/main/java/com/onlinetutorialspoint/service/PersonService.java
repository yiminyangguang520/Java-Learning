package com.onlinetutorialspoint.service;

import com.onlinetutorialspoint.model.db1.Person;
import com.onlinetutorialspoint.repository.db1.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Service
public class PersonService {

  @Autowired
  private PersonRepository personRepo;

  public List<Person> getAllPersons() {
    return (List<Person>) personRepo.findAll();
  }

  public Person savePerson(Person person) {
    return personRepo.save(person);
  }
}