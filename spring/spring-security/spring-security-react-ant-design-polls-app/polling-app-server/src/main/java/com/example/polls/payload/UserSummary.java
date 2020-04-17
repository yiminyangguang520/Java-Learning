package com.example.polls.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author min
 */
@Getter
@Setter
@AllArgsConstructor
public class UserSummary {

  private Long id;
  private String username;
  private String name;
}
