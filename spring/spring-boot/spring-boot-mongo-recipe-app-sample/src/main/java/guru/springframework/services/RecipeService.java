package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import java.util.Set;

/**
 *
 * @author jt
 * @date 6/13/17
 */
public interface RecipeService {

  Set<Recipe> getRecipes();

  Recipe findById(String id);

  RecipeCommand findCommandById(String id);

  RecipeCommand saveRecipeCommand(RecipeCommand command);

  void deleteById(String idToDelete);
}
