package com.technicalkeeda.dao;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.previousOperation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import com.technicalkeeda.bean.Car;
import com.technicalkeeda.bean.SalesReport;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

/**
 * @author min
 */
public class CarDaoImpl implements CarDao {

  private static final String COLLECTION = "cars";

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public void create(Car car) {
    if (car != null) {
      this.mongoTemplate.insert(car, COLLECTION);
    }
  }

  @Override
  public void drop() {
    this.mongoTemplate.dropCollection(COLLECTION);
  }

  @Override
  public List<SalesReport> aggregationByAll() {
    Aggregation aggregation = newAggregation(
        group("brand", "salesyear").sum("numberOfCars").as("total"),
        sort(Sort.Direction.ASC, previousOperation(), "brand")
    );

    AggregationResults<SalesReport> groupResults = mongoTemplate.aggregate(
        aggregation, Car.class, SalesReport.class);

    List<SalesReport> salesReport = groupResults.getMappedResults();

    return salesReport;
  }


  @Override
  public List<SalesReport> aggregationByYear(int year) {
    Aggregation aggregation = newAggregation(
        match(Criteria.where("salesyear").is(year)),
        group("brand", "salesyear").sum("numberOfCars").as("total"),
        sort(Sort.Direction.ASC, previousOperation(), "brand")
    );

    AggregationResults<SalesReport> groupResults = mongoTemplate.aggregate(
        aggregation, Car.class, SalesReport.class);

    List<SalesReport> salesReport = groupResults.getMappedResults();

    return salesReport;
  }
}