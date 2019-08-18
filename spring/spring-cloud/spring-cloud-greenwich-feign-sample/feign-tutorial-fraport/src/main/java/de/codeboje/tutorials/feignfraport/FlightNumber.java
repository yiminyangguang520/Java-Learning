package de.codeboje.tutorials.feignfraport;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author litz-a
 */
@Data
@NoArgsConstructor
public class FlightNumber {

  private String airlineCode;
  private String trackNumber;
}
