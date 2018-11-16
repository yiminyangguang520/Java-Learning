package guru.springframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author jt
 * @date 6/21/17
 */
@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {

  private String id;
  private String description;
}
