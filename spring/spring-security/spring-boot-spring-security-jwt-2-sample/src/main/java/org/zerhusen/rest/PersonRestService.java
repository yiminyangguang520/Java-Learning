package org.zerhusen.rest;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class PersonRestService {

  private static final List<Person> PERSON_LIST;

  static {
    PERSON_LIST = new ArrayList<>();
    PERSON_LIST.add(new Person("Hello", "World"));
    PERSON_LIST.add(new Person("Foo", "Bar"));
  }

  @RequestMapping(path = "/persons", method = RequestMethod.GET)
  public static List<Person> getPersons() {
    return PERSON_LIST;
  }

  @RequestMapping(path = "/persons/{name}", method = RequestMethod.GET)
  public static Person getPerson(@PathVariable("name") String name) {
    return PERSON_LIST.stream()
      .filter(person -> name.equalsIgnoreCase(person.getName()))
      .findAny().orElse(null);
  }
}
