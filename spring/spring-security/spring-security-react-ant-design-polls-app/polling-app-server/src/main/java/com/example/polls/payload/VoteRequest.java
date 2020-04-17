package com.example.polls.payload;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author min
 */
@Getter
@Setter
public class VoteRequest {

  @NotNull
  private Long choiceId;
}

