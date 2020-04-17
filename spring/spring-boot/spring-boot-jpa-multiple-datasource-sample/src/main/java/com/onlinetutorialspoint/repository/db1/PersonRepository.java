package com.onlinetutorialspoint.repository.db1;

import com.onlinetutorialspoint.model.db1.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}