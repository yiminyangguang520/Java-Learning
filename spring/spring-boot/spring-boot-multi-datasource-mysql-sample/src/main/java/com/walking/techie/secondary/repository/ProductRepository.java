package com.walking.techie.secondary.repository;

import com.walking.techie.model.secondary.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
