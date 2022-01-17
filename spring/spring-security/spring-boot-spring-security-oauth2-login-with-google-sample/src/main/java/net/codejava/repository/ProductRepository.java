package net.codejava.repository;

import net.codejava.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author min
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
