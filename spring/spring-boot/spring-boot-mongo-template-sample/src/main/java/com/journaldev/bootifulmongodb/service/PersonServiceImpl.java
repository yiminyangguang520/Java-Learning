package com.journaldev.bootifulmongodb.service;

import com.journaldev.bootifulmongodb.model.Person;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author litz-a
 */
@Repository
public class PersonServiceImpl implements PersonService {

  private final MongoTemplate mongoTemplate;

  @Autowired
  public PersonServiceImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public Person savePerson(Person person) {
    mongoTemplate.save(person);
    return person;
  }

  @Override
  public List<Person> getAllPerson() {
    return mongoTemplate.findAll(Person.class);
  }

  @Override
  public List<Person> getAllPersonPaginated(int pageNumber, int pageSize) {
    Query query = new Query();
    query.skip(pageNumber * pageSize);
    query.limit(pageSize);
    return mongoTemplate.find(query, Person.class);
  }

  @Override
  public Person findOneByName(String name) {
    Query query = new Query();
    query.addCriteria(Criteria.where("name").is(name));
    return mongoTemplate.findOne(query, Person.class);
  }

  @Override
  public List<Person> findByName(String name) {
    Query query = new Query();
    query.addCriteria(Criteria.where("name").is(name));
    return mongoTemplate.find(query, Person.class);
  }

  @Override
  public List<Person> findByBirthDateAfter(Date date) {
    Query query = new Query();
    query.addCriteria(Criteria.where("dateOfBirth").gt(date));
    return mongoTemplate.find(query, Person.class);
  }

  @Override
  public List<Person> findByAgeRange(int lowerBound, int upperBound) {
    Query query = new Query();
    query.addCriteria(Criteria.where("age").gt(lowerBound)
        .andOperator(Criteria.where("age").lt(upperBound)));
    return mongoTemplate.find(query, Person.class);
  }

  @Override
  public List<Person> findByFavoriteBooks(String favoriteBook) {
    Query query = new Query();
    query.addCriteria(Criteria.where("favoriteBooks").in(favoriteBook));
    return mongoTemplate.find(query, Person.class);
  }

  @Override
  public void updateMultiplePersonAge() {
    Query query = new Query();
    Update update = new Update().inc("age", 1);
    mongoTemplate.findAndModify(query, update, Person.class);;
  }

  @Override
  public Person updateOnePerson(Person person) {
    mongoTemplate.save(person);
    return person;
  }

  @Override
  public void deletePerson(Person person) {
    mongoTemplate.remove(person);
  }
}
