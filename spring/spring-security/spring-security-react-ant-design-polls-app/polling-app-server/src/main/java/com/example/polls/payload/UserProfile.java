package com.example.polls.payload;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author min
 */
@Getter
@Setter
@AllArgsConstructor
public class UserProfile {

  private Long id;
  private String username;
  private String name;
  private Instant joinedAt;
  private Long pollCount;
  private Long voteCount;
}
