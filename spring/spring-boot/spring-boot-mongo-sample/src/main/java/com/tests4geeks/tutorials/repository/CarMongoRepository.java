package com.tests4geeks.tutorials.repository;

import com.tests4geeks.tutorials.model.Car;
import org.springframework.data.repository.CrudRepository;

/**
 * @author min
 */
public interface CarMongoRepository extends CrudRepository<Car, String> {

}