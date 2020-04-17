package com.example.polls.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author min
 */
@Getter
@Setter
public class PollResponse {

  private Long id;
  private String question;
  private List<ChoiceResponse> choices;
  private UserSummary createdBy;
  private Instant creationDateTime;
  private Instant expirationDateTime;
  private Boolean expired;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Long selectedChoice;
  private Long totalVotes;
}
