package com.tests4geeks.tutorials.repository;

import com.tests4geeks.tutorials.model.Car;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
public class CarSearchRepository {

  @Autowired
  MongoTemplate mongoTemplate;

  public Collection<Car> searchCars(String text) {
    return mongoTemplate.find(Query.query(new Criteria()
        .orOperator(Criteria.where("description").regex(text, "i"),
            Criteria.where("make").regex(text, "i"),
            Criteria.where("model").regex(text, "i"))
    ), Car.class);
  }

}
