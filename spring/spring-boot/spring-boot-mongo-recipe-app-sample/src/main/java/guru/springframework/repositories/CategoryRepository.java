package guru.springframework.repositories;

import guru.springframework.domain.Category;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jt
 * @date 6/13/17
 */
public interface CategoryRepository extends CrudRepository<Category, String> {

  /**
   * findByDescription
   * @param description
   * @return
   */
  Optional<Category> findByDescription(String description);
}
