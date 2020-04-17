package de.codeboje.tutorials.feignfraport;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author min
 */
@Data
@NoArgsConstructor
public class FlightNumber {

  private String airlineCode;
  private String trackNumber;
}
