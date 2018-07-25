package com.aak.api;

import com.aak.domain.Product;
import com.aak.repository.ProductRepository;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ahmed
 * @date 30.5.18
 */

@RestController
@RequestMapping(value = "/product")
public class ProductController {

  private final ProductRepository productRepository;

  public ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @GetMapping
  public List<Product> products() {
    return productRepository.findAll();
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_PRODUCT_ADMIN')")
  public Product getProduct(@PathVariable("id") Long id) {
    return productRepository.findById(id)
        .orElse(null);
  }

  @GetMapping("/search/")
  @PreAuthorize("hasRole('ROLE_PRODUCT_ADMIN')")
  public Product findByName(@RequestParam("name") String name) {
    return productRepository.findByNameLike("%" + name + "%");
  }

  @GetMapping("/products")
  @PreAuthorize("hasRole('ROLE_PRODUCT_ADMIN')")
  public List<Product> findAll() {
    return productRepository.findAll();
  }
}
