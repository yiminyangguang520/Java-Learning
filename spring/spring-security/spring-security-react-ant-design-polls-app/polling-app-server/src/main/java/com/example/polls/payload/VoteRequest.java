package com.example.polls.payload;

import javax.validation.constraints.NotNull;

/**
 * @author min
 */
public class VoteRequest {

  @NotNull
  private Long choiceId;

  public Long getChoiceId() {
    return choiceId;
  }

  public void setChoiceId(Long choiceId) {
    this.choiceId = choiceId;
  }
}

