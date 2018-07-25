package com.aak.repository;

import com.aak.domain.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ahmed
 * @date 30.5.18
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  /**
   * findByNameLike
   * @param name
   * @return
   */
  Product findByNameLike(String name);

  /**
   * findAll
   * @return
   */
  List<Product> findAll();
}
