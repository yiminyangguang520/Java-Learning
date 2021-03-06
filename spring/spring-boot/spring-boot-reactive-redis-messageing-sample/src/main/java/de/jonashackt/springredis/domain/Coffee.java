package de.jonashackt.springredis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author litz-a
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {

  private String id;
  private String name;
}