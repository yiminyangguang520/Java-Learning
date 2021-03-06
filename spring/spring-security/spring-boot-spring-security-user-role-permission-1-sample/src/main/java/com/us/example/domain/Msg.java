package com.us.example.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author min
 */
@Getter
@Setter
@AllArgsConstructor
public class Msg {

  private String title;
  private String content;
  private String etraInfo;
}
