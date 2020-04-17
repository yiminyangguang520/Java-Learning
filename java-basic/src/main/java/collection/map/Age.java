package collection.map;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author min
 */
@Builder
@Data
@ToString
public class Age {

  private Integer year;
  private Integer month;
}
