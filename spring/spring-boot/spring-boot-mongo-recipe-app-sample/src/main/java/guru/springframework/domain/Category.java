package guru.springframework.domain;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author jt
 * @date 6/13/17
 */
@Getter
@Setter
public class Category {

  private String id;
  private String description;
  private Set<Recipe> recipes;
}
