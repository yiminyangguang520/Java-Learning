package com.journaldev.bootifulmongodb;


import com.journaldev.bootifulmongodb.model.Person;
import com.journaldev.bootifulmongodb.service.PersonService;
import java.util.Arrays;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author min
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoApplicationTest {

  private static final Logger LOG = LoggerFactory.getLogger("MongoApplicationTest");

  @Autowired
  private PersonService personService;

  @Test
  public void contextLoads() {
    personService.savePerson(new Person("Shubham", Arrays.asList("Harry potter", "Waking Up"), new Date(769372200000L)));
    personService.savePerson(new Person("Sergey", Arrays.asList("Startup Guides", "Java"), new Date(664309800000L)));
    personService.savePerson(new Person("David", Arrays.asList("Harry potter", "Success"), new Date(695845800000L)));
    personService.savePerson(new Person("Ivan", Arrays.asList("Secrets of Butene", "Meeting Success"), new Date(569615400000L)));
    personService.savePerson(new Person("Sergey", Arrays.asList("Harry potter", "Startup Guides"), new Date(348777000000L)));
    LOG.info("Getting all data from MongoDB: \n{}", personService.getAllPerson());
    LOG.info("Getting paginated data from MongoDB: \n{}", personService.getAllPersonPaginated(0, 2));
    LOG.info("Getting all person By name 'Sergey': {}", personService.findByName("Sergey"));
    LOG.info("Getting one person By name 'Sergey': {}", personService.findOneByName("Sergey"));
    LOG.info("Getting people between age 22 & 26: {}", personService.findByAgeRange(22, 26));
  }
}