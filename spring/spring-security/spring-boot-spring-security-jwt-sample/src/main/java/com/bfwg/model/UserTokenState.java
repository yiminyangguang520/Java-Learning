package com.bfwg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author fan.jin
 * @date 2016-10-17
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenState {

  private String access_token;
  private Long expires_in;
}