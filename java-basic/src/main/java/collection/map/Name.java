package collection.map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author litz-a
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Name {

  private String firstName;
  private String lastName;

}