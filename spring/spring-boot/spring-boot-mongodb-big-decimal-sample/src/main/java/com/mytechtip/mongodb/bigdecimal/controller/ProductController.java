package com.mytechtip.mongodb.bigdecimal.controller;

import com.mytechtip.mongodb.bigdecimal.dao.ProductRepository;
import com.mytechtip.mongodb.bigdecimal.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class ProductController {

  @Autowired
  private ProductRepository productRepository;

  @GetMapping(value = "/products")
  List<Product> getProducts() {
    return productRepository.findAllByOrderByPrice();
  }

  @PostMapping(value = "/products/save")
  Product save(@RequestBody Product product) {
    return productRepository.save(product);
  }
}
