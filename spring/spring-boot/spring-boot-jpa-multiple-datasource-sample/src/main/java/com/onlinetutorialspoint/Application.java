package com.onlinetutorialspoint;

import com.onlinetutorialspoint.model.db1.Person;
import com.onlinetutorialspoint.model.db2.Department;
import com.onlinetutorialspoint.repository.db1.PersonRepository;
import com.onlinetutorialspoint.service.DepartmentService;
import com.onlinetutorialspoint.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author litz-a
 */
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Autowired
  PersonService personService;

  @Autowired
  DepartmentService deptService;

  @Autowired
  @Bean
  public CommandLineRunner run(PersonRepository repository) {
    return (args) -> {
      //savePersonDetails();
      //saveDepartmentDetails();
      getAllPerson();
      getDepartments();
    };
  }

  public Person savePersonDetails() {
    Person person = new Person();
    person.setName("Chandra Shekhar Goka");
    person.setCity("Hyderabad");
    return personService.savePerson(person);
  }

  public Department saveDepartmentDetails() {
    Department dept = new Department();
    dept.setName("IT");
    return deptService.saveDepartment(dept);
  }

  public void getPersonDetails() {

  }

  public void getAllPerson() {
    List<Person> persons = personService.getAllPersons();
    persons.forEach(System.out::println);
  }

  public void getDepartments() {
    List<Department> depts = deptService.getAllDepartment();
    depts.forEach(System.out::println);
  }

}
