package com.lmonkiewicz.example.model;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lmonkiewicz
 * @date 20.03.2017
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

  @NotNull
  private String title;
  @NotNull
  private String message;
}
