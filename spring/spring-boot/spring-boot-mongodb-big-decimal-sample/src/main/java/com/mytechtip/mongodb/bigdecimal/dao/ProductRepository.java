package com.mytechtip.mongodb.bigdecimal.dao;

import com.mytechtip.mongodb.bigdecimal.model.Product;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author cwang
 */
public interface ProductRepository extends MongoRepository<Product, String> {

  List<Product> findAllByOrderByPrice();
}
