package guru.springframework.commands;

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
public class NotesCommand {

  private String id;
  private String recipeNotes;

}
