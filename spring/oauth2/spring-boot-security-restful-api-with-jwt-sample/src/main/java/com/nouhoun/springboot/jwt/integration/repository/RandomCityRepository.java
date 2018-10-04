package com.nouhoun.springboot.jwt.integration.repository;

import com.nouhoun.springboot.jwt.integration.domain.RandomCity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nydiarra on 10/05/17.
 * @author nydiarra
 */
public interface RandomCityRepository extends CrudRepository<RandomCity, Long> {

}
