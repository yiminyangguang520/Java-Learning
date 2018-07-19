package com.lee.controller;

import com.lee.entity.Person;
import com.lee.service.PersonService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class CacheController {

  @Autowired
  PersonService personService;

  @Autowired
  EhCacheCacheManager cacheManager;

  @RequestMapping("/put")
  public long put(@RequestBody Person person) {
    Person p = personService.save(person);
    return p.getId();
  }

  @RequestMapping("/able")
  public Optional<Person> cacheable(@RequestBody Person person) {
    System.out.println(cacheManager.toString());
    return personService.findOne(person);
  }

  @RequestMapping("/evit")
  public String evit(Long id) {

    personService.remove(id);
    return "ok";
  }

}