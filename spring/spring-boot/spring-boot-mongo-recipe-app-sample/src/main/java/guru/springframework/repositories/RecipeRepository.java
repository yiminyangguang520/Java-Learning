package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jt
 * @date 6/13/17
 */
public interface RecipeRepository extends CrudRepository<Recipe, String> {

}
