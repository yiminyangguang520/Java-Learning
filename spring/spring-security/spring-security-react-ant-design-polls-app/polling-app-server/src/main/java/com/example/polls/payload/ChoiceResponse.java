package com.example.polls.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * @author litz-a
 */
@Getter
@Setter
public class ChoiceResponse {

  private long id;
  private String text;
  private long voteCount;
}
