package com.us.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yangyibo
 * @date 17/1/17
 */
@Getter
@Setter
@AllArgsConstructor
public class Msg {

  private String title;
  private String content;
  private String etraInfo;
}
