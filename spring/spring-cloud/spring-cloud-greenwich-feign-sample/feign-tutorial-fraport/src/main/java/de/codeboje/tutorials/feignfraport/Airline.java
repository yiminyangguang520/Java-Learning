package de.codeboje.tutorials.feignfraport;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author min
 */
@Data
@NoArgsConstructor
public class Airline {

  private String iataCode;
  private String icaoCode;
  private String airlineDisplayCode;
  private String name;
}
