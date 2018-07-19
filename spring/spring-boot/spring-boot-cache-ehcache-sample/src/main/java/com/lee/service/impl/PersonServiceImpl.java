package com.lee.service.impl;

import com.lee.entity.Person;
import com.lee.repository.PersonRepository;
import com.lee.service.PersonService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  PersonRepository personRepository;

  @Override
  @CachePut(value = "people", key = "#person.id")
  public Person save(Person person) {
    Person p = personRepository.save(person);
    System.out.println("为id、key为:" + p.getId() + "数据做了缓存");
    return p;
  }

  @Override
  @CacheEvict(value = "people")
  public void remove(Long id) {
    System.out.println("删除了id、key为" + id + "的数据缓存");
  }

  @Override
  @Cacheable(value = "people", key = "#person.id")
  public Optional<Person> findOne(Person person) {
    Optional<Person> p = personRepository.findById(person.getId());
    p.ifPresent(man -> System.out.println("为id、key为:" + man.getId() + "数据做了缓存"));
    return p;
  }
}
