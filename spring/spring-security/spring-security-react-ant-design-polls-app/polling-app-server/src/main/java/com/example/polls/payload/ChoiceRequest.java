package com.example.polls.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author min
 */
@Getter
@Setter
public class ChoiceRequest {

  @NotBlank
  @Size(max = 40)
  private String text;
}
