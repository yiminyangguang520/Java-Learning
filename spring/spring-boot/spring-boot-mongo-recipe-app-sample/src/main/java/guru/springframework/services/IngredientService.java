package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

/**
 *
 * @author jt
 * @date 6/27/17
 */
public interface IngredientService {

  IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

  IngredientCommand saveIngredientCommand(IngredientCommand command);

  void deleteById(String recipeId, String idToDelete);
}
