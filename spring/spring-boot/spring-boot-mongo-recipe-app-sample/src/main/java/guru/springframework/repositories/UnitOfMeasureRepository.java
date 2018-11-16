package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jt
 * @date 6/13/17
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, String> {

  /**
   * findByDescription
   * @param description
   * @return
   */
  Optional<UnitOfMeasure> findByDescription(String description);
}
