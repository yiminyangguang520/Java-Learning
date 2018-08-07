package com.example.polls.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author rajeevkumarsingh
 * @date 19/08/17
 */
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {

  private Boolean success;
  private String message;
}
