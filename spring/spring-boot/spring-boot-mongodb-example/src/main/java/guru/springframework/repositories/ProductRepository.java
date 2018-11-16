package guru.springframework.repositories;

import guru.springframework.domain.Product;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jt
 * @date 1/10/17
 */
public interface ProductRepository extends CrudRepository<Product, String> {

}
