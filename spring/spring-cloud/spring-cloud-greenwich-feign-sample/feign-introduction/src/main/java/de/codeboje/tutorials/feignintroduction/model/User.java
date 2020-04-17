package de.codeboje.tutorials.feignintroduction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author min
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private String username;
  private String password;
}
