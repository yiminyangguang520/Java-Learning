package guru.springframework.commands;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author jt
 * @date 6/21/17
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

  private String id;
  private String recipeId;
  private String description;
  private BigDecimal amount;
  private UnitOfMeasureCommand uom;
}
